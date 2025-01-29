package br.com.company.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDto {

	
	private String nome;
	private String login;	
	private String empresa;	
	private String email;	
	private String active;
	
	
	
}
