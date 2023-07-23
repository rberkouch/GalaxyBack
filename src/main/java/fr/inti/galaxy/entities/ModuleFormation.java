package fr.inti.galaxy.entities;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleFormation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date operationDate;
	private String moduleName;
	private String imageUrl;
	@ManyToMany
	private List<Formation> formations;

	@ManyToMany
	@JoinTable(name = "tbl_modules_documents", joinColumns = @JoinColumn(name = "moduleId"), inverseJoinColumns = @JoinColumn(name = "documentId"))
	private List<Document> documents;
}
