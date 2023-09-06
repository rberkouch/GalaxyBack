package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DocumentProjetDTO {
	private Long id;
	private Date operationDate;
	private String title;
	private String description;
	private int timeConstraint;
	private String niveau;
	private List<UtilisateurDTO> utilisateurs;
}
