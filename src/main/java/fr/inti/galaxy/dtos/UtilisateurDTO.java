package fr.inti.galaxy.dtos;

import java.util.List;

import lombok.Data;

@Data
public class UtilisateurDTO {
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String profileImageUrl;
	private boolean isActive;
	private boolean firstLogin;
	private List<DocumentDTO> documents;
	private List<DocumentProjetDTO> documentProjets;
	private List<NotificationDTO> notifications;

}
