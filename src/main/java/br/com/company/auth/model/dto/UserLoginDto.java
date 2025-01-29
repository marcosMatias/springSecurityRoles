package br.com.company.auth.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

	@NotBlank(message = "o login deve ser informado.")
	private String login;
	
	
	public void setLogin(String login) {
        this.login = login != null ? login.toLowerCase() : null;
    }
	
	
	
}
