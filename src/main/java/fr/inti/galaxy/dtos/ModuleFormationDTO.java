package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ModuleFormationDTO {
	private Long id;
	private Date operationDate;
	private String moduleName;
	private String imageUrl;
	private List<FormationDTO> formations;
	private List<DocumentDTO> documents;
}
