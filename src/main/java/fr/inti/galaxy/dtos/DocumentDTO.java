package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DocumentDTO {
	private Long id;
	private Date operationDate;
	private String documentName;
	private String documentUrl;
	private List<UtilisateurDTO> utilisateurs;

}
