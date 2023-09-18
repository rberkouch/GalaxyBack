package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Livrable;

@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Integer> {
	@Query("select l from Livrable l where l.repoName like :kw")
	List<Livrable> searchLivrable(@Param("kw") String keyword);

	@Query(value = "SELECT * FROM document_projet WHERE type='livrable' and id  IN(select document_projet_id from document_projet_utilisateurs where utilisateurs_user_id IN (select user_id from utilisateur where username=?1))", nativeQuery = true)
	List<Livrable> findLivrablesParUsername(String username);
}
