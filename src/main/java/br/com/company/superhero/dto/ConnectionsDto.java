package br.com.company.superhero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionsDto {

	private String groupAffiliation;
	private String relatives;

}
