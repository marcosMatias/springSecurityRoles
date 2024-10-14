package br.com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.company.config.security.TokenConfigService;
import br.com.company.model.UserModel;
import br.com.company.model.dto.AuthenticateRequestDto;
import br.com.company.model.dto.AuthenticateResponseDto;



@Service
public class AuthenticateService {
	
	
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenConfigService tokenService;


	
	public AuthenticateResponseDto executeLoginService(AuthenticateRequestDto authenticateRecord) {

		var authenticationtoken = new UsernamePasswordAuthenticationToken(authenticateRecord.username(),authenticateRecord.password());
		var authentication = manager.authenticate(authenticationtoken);

		return tokenService.gerarTokenService((UserModel) authentication.getPrincipal());

	}

}
