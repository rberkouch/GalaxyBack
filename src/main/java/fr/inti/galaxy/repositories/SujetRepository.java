package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Sujet;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Integer> {
	@Query(value = "SELECT * FROM document_projet WHERE type='sujet' and id  IN(select document_projet_id from document_projet_utilisateurs where utilisateurs_user_id IN (select user_id from utilisateur where username=?1))", nativeQuery = true)
	List<Sujet> findSujetsByUsername(String username);
}
