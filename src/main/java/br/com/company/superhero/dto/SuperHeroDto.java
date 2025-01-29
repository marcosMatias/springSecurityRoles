package br.com.company.superhero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroDto {
	private String id;
    private String name;
    private PowerStatsDto powerstats;
    private BiographyDto biography;
    private AppearanceDto appearance;
    private WorkDto work;
    private ConnectionsDto connections;
    private SuperheroImageDto image;

}
