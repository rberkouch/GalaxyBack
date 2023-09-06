package fr.inti.galaxy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
	@Column(length = 65555)
	private String functionality;
	@Column(length = 65555)
	private String stackTechnique;
	@Column(length = 65555)
	private String expectedDelivery;
	@Column(length = 65555)
	private String developerRating;
}
