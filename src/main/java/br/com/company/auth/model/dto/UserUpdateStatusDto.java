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
public class UserUpdateStatusDto {

	@NotBlank(message = "o login é obrigatorio")
	private String login;		
	@NotBlank(message = "o status é obrigatorio")
	private String active;
	
}
