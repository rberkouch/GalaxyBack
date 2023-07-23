package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
public class DocumentDTO {
    private Long id;
    private Date operationDate;
    private String documentName;
    private String documentUrl;
	private List<UtilisateurDTO> utilisateurs;

}
