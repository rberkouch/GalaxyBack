package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.inti.galaxy.entities.Sujet;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Integer> {
	@Query(value = "SELECT * FROM document_projet WHERE type='sujet' and id  IN(select document_projet_id from document_projet_utilisateurs where utilisateurs_user_id IN (select user_id from utilisateur where username=?1))", nativeQuery = true)
	List<Sujet> findSujetsByUsername(String username);
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO document_projet_utilisateurs(document_projet_id,utilisateurs_user_id) VALUES (:idSujet,:idUser)", nativeQuery = true)
	void insertSujetUtilisateur(@Param("idSujet")Long idSujet,@Param("idUser") String idUser);

}
