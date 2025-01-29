package br.com.company.config.security;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.company.auth.repository.UserRepository;
import br.com.company.auth.service.MonitorAcessService;
import br.com.company.service.util.UtilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	
	private final TokenConfigService tokenService;
	private final UserRepository usuarioRepository;
	private final UtilService utilService;
	private final MonitorAcessService monitorAcessService;


	public SecurityFilter(TokenConfigService tokenService, UserRepository usuarioRepository, UtilService utilService,MonitorAcessService monitorAcessService) {

		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
		this.utilService = utilService;
		this.monitorAcessService = monitorAcessService;
	
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)	throws ServletException, IOException {		
		
		

		try {
			var tokenJwt = recuperarToken(request);

			if (tokenJwt != null) {

				var subject = tokenService.getSubject(tokenJwt);
				var usuario = usuarioRepository.findByLoginIgnoreCase(subject);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);
								
				monitorAcessService.logAudit(usuario.getUsername(), request);
			}

			filterChain.doFilter(request, response);

		} catch (Exception ex) {

			utilService.sendErrorResponseFilter(HttpStatus.UNAUTHORIZED.value(), ex.getMessage().toString(),"uri=" + request.getRequestURI().toString(), response);

		}
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		
		return null;
		
	}
	
	
	
}
