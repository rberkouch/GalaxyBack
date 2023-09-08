package fr.inti.galaxy.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LivrableDTO extends DocumentProjetDTO {
	private String repoNameBack;
	private String repoNameFront;
	private String gitUrlBack;
	private String gitUrlFront;
}
