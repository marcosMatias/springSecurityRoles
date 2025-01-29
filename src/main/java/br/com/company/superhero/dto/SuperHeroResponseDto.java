package br.com.company.superhero.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroResponseDto {
	
	private String response;
    private String resultsFor;
    private List<SuperHeroDto> results;

}
