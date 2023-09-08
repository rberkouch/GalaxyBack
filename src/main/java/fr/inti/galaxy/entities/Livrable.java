package fr.inti.galaxy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("livrable")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Livrable extends DocumentProjet {
	@Column(length = 65555)
	private String repoNameBack;
	@Column(length = 65555)
	private String repoNameFront;
	@Column(length = 65555)
	private String gitUrlBack;
	@Column(length = 65555)
	private String gitUrlFront;
}
