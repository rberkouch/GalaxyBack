package fr.inti.galaxy.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class SujetDTO extends DocumentProjetDTO {
	private String description;
	private String functionality;
	private String stackTechnique;
	private String expectedDelivery;
	private String developerRating;
}
