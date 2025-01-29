package br.com.company.auth.model.dto;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorAcessDto {
	
	private String login;
	private String httpMethod;
	private String uri;
	private LocalDateTime  acessDate;
	private String ipAdress;

}
