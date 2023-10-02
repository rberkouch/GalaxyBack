package fr.inti.galaxy.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DocumentProjetUtilisateursKey implements Serializable {
	@Column(name = "document_projet_id")
	private Long documentProjetId;

	@Column(name = "utilisateurs_user_id")
	private String utilisateursUserId;
}
