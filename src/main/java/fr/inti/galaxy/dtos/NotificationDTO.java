package fr.inti.galaxy.dtos;

import fr.inti.galaxy.entities.Sujet;
import fr.inti.galaxy.entities.Utilisateur;
import lombok.Data;

@Data
public class NotificationDTO {
	private long id;
	private String message;
	private Utilisateur utilisateur;
	private Sujet sujet;
	private int statut;
	private String type;
}
