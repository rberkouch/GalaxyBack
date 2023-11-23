package fr.inti.galaxy.repositories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import fr.inti.galaxy.entities.Sujet;

public interface SujetService {
	public List<Sujet> getAll();

	public Sujet getSujetById(Integer id);

	public Sujet save(Sujet Sujet);

	public void delete(int id);

	List<Sujet> findSujetsByUsername(String username);

	void affectSujetToUser(Long idSujet, String idUser);

	void deleteOneFromDocumentProjetUtilisateurs(Long idSujet, String idUser);

	<T> List<Optional<T>> findAllDocumentProjetUtilisateurs(Long idSujet, String idUser);
	
	public void sendMail(String email, Sujet sujet);
}
