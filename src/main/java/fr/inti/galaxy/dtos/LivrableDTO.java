package fr.inti.galaxy.dtos;

import java.util.List;

import fr.inti.galaxy.entities.Avis;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LivrableDTO extends DocumentProjetDTO {
	private String repoName;
	private String gitUrl;
	private List<AvisDTO> avis;
}
