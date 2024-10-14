package br.com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.model.dto.AuthenticateRequestDto;
import br.com.company.model.dto.AuthenticateResponseDto;
import br.com.company.service.AuthenticateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticateController {
	
	
	@Autowired
	private AuthenticateService authenticateService;
	
	@PostMapping
	public AuthenticateResponseDto executeLogin(@RequestBody @Valid AuthenticateRequestDto  authenticateRecord) {

		return authenticateService.executeLoginService(authenticateRecord);
	}

}
