package br.com.company.auth.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordDto {
	
	@NotBlank(message = "o login é obrigatorio")
	private String login;		
	@NotBlank(message = "o password é obrigatorio")
	private String password;

}
