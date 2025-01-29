package br.com.company.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.company.auth.model.UserModel;
import br.com.company.auth.model.dto.AuthenticateRequestDto;
import br.com.company.auth.model.dto.AuthenticateResponseDto;
import br.com.company.config.security.TokenConfigService;


@Service
public class AuthenticateService {
	
	
	
	private final AuthenticationManager manager;
	private final TokenConfigService tokenService;


	
	public AuthenticateService(AuthenticationManager manager, TokenConfigService tokenService) {
		
		this.manager = manager;
		this.tokenService = tokenService;
	}


	@Transactional(readOnly = true)
	public AuthenticateResponseDto executeLoginService(AuthenticateRequestDto authenticateRecord) {

		var authenticationtoken = new UsernamePasswordAuthenticationToken(authenticateRecord.username(),authenticateRecord.password());
		var authentication = manager.authenticate(authenticationtoken);

		return tokenService.gerarTokenService((UserModel) authentication.getPrincipal());

	}

}
