package br.com.company.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResponseDto {
	
	private String date;
	private String usuario;
	private String acessToken;
	private Long expiresIn;
	private String empresa;

}
