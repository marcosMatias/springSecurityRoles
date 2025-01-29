package br.com.company.auth.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.auth.model.dto.AuthenticateRequestDto;
import br.com.company.auth.model.dto.AuthenticateResponseDto;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.service.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name="Auth")
public class AuthenticationController {


	private final AuthenticateService authenticateService;
	
	public AuthenticationController(AuthenticateService authenticateService) {
		this.authenticateService =authenticateService;
	}
	
	
	
	
	@PostMapping
	@Operation(summary = "Autenticar Usuário",method = "POST")		
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Ok",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = AuthenticateResponseDto.class))}), 
	        @ApiResponse(responseCode = "400", description = "Bad request",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}), 
	        @ApiResponse(responseCode = "401", description = "Unauthorized",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "403", description = "Forbidden",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))}),
	        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content (mediaType = "application/json",schema = @Schema(implementation = ResponseDto.class))})})	        		 
	public AuthenticateResponseDto executeLogin(@RequestBody @Valid AuthenticateRequestDto  authenticateRecord) {

		return authenticateService.executeLoginService(authenticateRecord);
	}
	
	
}
