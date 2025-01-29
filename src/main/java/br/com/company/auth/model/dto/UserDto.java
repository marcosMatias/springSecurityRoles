package br.com.company.auth.model.dto;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank(message = "o nome é obrigatorio")
	private String nome;
    @JsonIgnore
	private String login;	
	@NotBlank(message = "o password é obrigatorio")
	private String password;	
	@NotBlank(message = "A empresa é obrigatorio")
	private String empresa;	
	@NotBlank(message = "O email é obrigatorio")
	private String email;	
	@Value("${user.active:S}")
	private String active;
	
	
	
	public void setLogin(String login) {
        this.login = login != null ? login.toLowerCase() : null;
    }
	
	
	public void setEmail(String email) {
        this.email = email != null ? email.toLowerCase() : null;
    }

}
