package fr.inti.galaxy.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_modules_documents", joinColumns = @JoinColumn(name = "moduleId",referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "documentId",referencedColumnName = "id"))
	private List<Document> documents;
}
