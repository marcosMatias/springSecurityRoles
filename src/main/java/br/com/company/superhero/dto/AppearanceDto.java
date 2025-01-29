package br.com.company.superhero.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceDto {

	private String gender;
    private String race;
    private List<String> height;
    private List<String> weight;
    private String eyeColor;
    private String hairColor;
}


