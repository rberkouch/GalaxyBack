package fr.inti.galaxy.dtos;

import fr.inti.galaxy.entities.DocumentProjet;
import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.models.DocumentProjetUtilisateursKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DocumentProjetUtilisateursDTO {
	private DocumentProjetUtilisateursKey documentProjetUtilisateursKey;
	private Utilisateur utilisateur;
	private DocumentProjet documentProjet;
	private boolean selected;
}
