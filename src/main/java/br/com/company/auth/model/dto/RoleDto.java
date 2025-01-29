package br.com.company.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
	
	@NotBlank(message = "a role é obrigatória")
	@JsonProperty("role")
	private String name;
	
	public void setName(String name) {
        this.name = name != null ? name.toUpperCase() : null;
    }

}
