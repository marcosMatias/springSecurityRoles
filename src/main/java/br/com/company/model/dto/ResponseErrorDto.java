package br.com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDto {

	private int statusCode;	
	private String data;
	private String message;
	private String description;
}
