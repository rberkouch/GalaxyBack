package fr.inti.galaxy.entities;

import fr.inti.galaxy.models.DocumentProjetUtilisateursKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_projet_utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentProjetUtilisateurs {
	@EmbeddedId
	private DocumentProjetUtilisateursKey documentProjetUtilisateursKey;
	@ManyToOne
	@MapsId("utilisateurs_user_id")
	@JoinColumn(name = "utilisateurs_user_id")
	private Utilisateur utilisateur;
	@ManyToOne
	@MapsId("document_projet_id")
	@JoinColumn(name = "document_projet_id")
	private DocumentProjet documentProjet;
	private boolean selected=false;
}
