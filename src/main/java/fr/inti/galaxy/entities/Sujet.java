package fr.inti.galaxy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("sujet")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Sujet extends DocumentProjet {
	@Column(length = Integer.MAX_VALUE)
	private String description;
	@Column(length = Integer.MAX_VALUE)
	private String functionality;
	@Column(length = Integer.MAX_VALUE)
	private String stackTechnique;
	@Column(length = Integer.MAX_VALUE)
	private String expectedDelivery;
	@Column(length = Integer.MAX_VALUE)
	private String developerRating;
	private Integer statut; //0: supprimé 1: pas encore activé 2: activé 3: demande de supp envoyé 
	//Le profil représente la technologie associée au sujet
	@ManyToOne
	private Profile profile;
}
