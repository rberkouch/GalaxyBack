package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ModuleFormationDTO {
	private Long id;
	private Date operationDate;
	private String moduleName;
	private String imageUrl;
	private List<FormationDTO> formations;
	private List<DocumentDTO> documents;
}
