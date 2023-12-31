package fr.inti.galaxy.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.inti.galaxy.security.AppRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(unique = true)
	private String email;
	private String profileImageUrl;

	private boolean isActive;
	private boolean firstLogin;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_utilisateurs_documents", joinColumns = @JoinColumn(name = "utilisateurId",referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "documentId",referencedColumnName = "id"))
	private List<Document> documents;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_utilisateurs_documents_projets", joinColumns = @JoinColumn(name = "utilisateurId",referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "documentId",referencedColumnName = "id"))
	private List<DocumentProjet> documentProjets;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JoinTable(name = "profil", joinColumns = @JoinColumn(name = "utilisateurId",referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId",referencedColumnName = "role"))
	private List<AppRole> roles;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profile profile;
	
	

}
