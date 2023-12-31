package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

	Utilisateur findByUsername(String username);

	Utilisateur findUserByEmail(String email);

	@Query("select u from Utilisateur u where u.lastName like :kw")
	List<Utilisateur> searchUser(@Param("kw") String keyword);

	@Query(value = "select * from utilisateur u join profil p on u.user_id = p.utilisateur_id join app_role a on p.role_id = a.role where a.role = 'APPRENANT' and u.last_name like :kw", nativeQuery = true)
	List<Utilisateur> findUsersIfRoleIsApprenant(@Param("kw") String lastname);
	
	@Query("SELECT u FROM Utilisateur u JOIN u.documentProjets dp WHERE TYPE(dp) = fr.inti.galaxy.entities.Sujet")
    List<Utilisateur> findUsersWithSujetDocumentProjet();
}
