package fr.inti.galaxy.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date operationDate;
	private String formationName;
	private String imageUrl;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbl_formations_modules", joinColumns = @JoinColumn(name = "formationId"), inverseJoinColumns = @JoinColumn(name = "moduleId"))
	private List<ModuleFormation> modules;
}
