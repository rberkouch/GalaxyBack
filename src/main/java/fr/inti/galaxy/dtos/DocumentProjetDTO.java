package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.Set;

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
	private int timeConstraint;
	private String level;
	private Set<DocumentProjetUtilisateursDTO> documentProjetUtilisateursDTOs;
}
