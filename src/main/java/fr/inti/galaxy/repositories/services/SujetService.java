package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Sujet;

public interface SujetService {
	public List<Sujet> getAll();

	public Sujet getSujetById(Integer id);

	public Sujet save(Sujet Sujet);

	public void delete(int id);

	List<Sujet> findSujetsByUsername(String username);

	void affectSujetToUser(Long idSujet, String idUser);

	void deleteOneFromDocumentProjetUtilisateurs(Long idSujet, String idUser);
}
