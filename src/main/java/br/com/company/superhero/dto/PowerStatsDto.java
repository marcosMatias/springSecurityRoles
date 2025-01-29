package br.com.company.superhero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStatsDto {

	private String intelligence;
	private String strength;
	private String speed;
	private String durability;
	private String power;
	private String combat;
}
