package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FormationDTO {
	private Long id;
	private Date operationDate;
	private String formationName;
	private String imageUrl;
	private List<ModuleFormationDTO> modules;
}
