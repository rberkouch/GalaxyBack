package fr.inti.galaxy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.inti.galaxy.entities.Sujet;
import fr.inti.galaxy.entities.Utilisateur;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Integer> {
	@Query(value = "SELECT * FROM document_projet WHERE type='sujet' and id  IN(select document_projet_id from document_projet_utilisateurs where utilisateurs_user_id IN (select user_id from utilisateur where username=?1))", nativeQuery = true)
	List<Sujet> findSujetsByUsername(String username);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO document_projet_utilisateurs(document_projet_id,utilisateurs_user_id) VALUES (:idSujet,:idUser)", nativeQuery = true)
	void affectSujetToUser(@Param("idSujet") Long idSujet, @Param("idUser") String idUser);

	@Modifying
	@Query(value = "DELETE FROM document_projet_utilisateurs WHERE document_projet_id = :idSujet and utilisateurs_user_id = :idUser", nativeQuery = true)
	void deleteOneFromDocumentProjetUtilisateurs(@Param("idSujet") Long idSujet, @Param("idUser") String idUser);

	@Query(value = "select * from document_projet_utilisateurs WHERE document_projet_id = :idSujet and utilisateurs_user_id = :idUser", nativeQuery = true)
	<T> List<Optional<T>> findAllDocumentProjetUtilisateurs(@Param("idSujet") Long idSujet,
			@Param("idUser") String idUser);
	
	@Query("select s.utilisateurs from Sujet s where s.id=:id ")
	public List<Utilisateur> chercherUserWithSujet(@Param("id") int id);

}
