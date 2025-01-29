package br.com.company.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePermissionDto {
	
	@NotBlank(message = "o login é obrigatorio")
	private String login;	
	
	@NotBlank(message = "a role é obrigatória")
	@JsonProperty("role")
	private String name;
	
	public void setName(String name) {
        this.name = name != null ? name.toUpperCase() : null;
    }

}
