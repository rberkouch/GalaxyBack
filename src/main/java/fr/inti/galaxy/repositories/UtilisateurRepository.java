package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.inti.galaxy.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

	Utilisateur findByUsername(String username);

	Utilisateur findUserByEmail(String email);
	
    @Query("select u from Utilisateur u where u.lastName like :kw")
    List<Utilisateur> searchUser(@Param("kw") String keyword);
}
