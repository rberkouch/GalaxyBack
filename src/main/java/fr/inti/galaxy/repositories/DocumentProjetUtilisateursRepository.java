package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.inti.galaxy.entities.DocumentProjetUtilisateurs;
import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.models.DocumentProjetUtilisateursKey;

@Repository
public interface DocumentProjetUtilisateursRepository
		extends JpaRepository<DocumentProjetUtilisateurs, DocumentProjetUtilisateursKey> {
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO document_projet_utilisateurs(document_projet_id,utilisateurs_user_id,selected) VALUES (:idSujet,:idUser,true)", nativeQuery = true)
	void affectSujetToUser(@Param("idSujet") Long idSujet, @Param("idUser") String idUser);

	@Modifying
	@Query(value = "DELETE FROM document_projet_utilisateurs WHERE document_projet_id = :idSujet and utilisateurs_user_id = :idUser", nativeQuery = true)
	void deleteOneFromDocumentProjetUtilisateurs(@Param("idSujet") Long idSujet, @Param("idUser") String idUser);

	@Query(value = "select * from document_projet_utilisateurs WHERE document_projet_id = :idSujet and utilisateurs_user_id = :idUser", nativeQuery = true)
	List<DocumentProjetUtilisateurs> findAllDocumentProjetUtilisateurs(@Param("idSujet") Long idSujet,
			@Param("idUser") String idUser);
}
