package fr.inti.galaxy.dtos;

import java.util.Date;
import java.util.List;

import fr.inti.galaxy.entities.Utilisateur;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AvisDTO {
	
	private Long id;
	private String texteAvis;


}
