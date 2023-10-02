package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.DocumentProjetUtilisateurs;

public interface DocumentProjetUtilisateursService {
	public List<DocumentProjetUtilisateurs> getAll();

	void affectSujetToUser(Long idSujet, String idUser);

	void deleteOneFromDocumentProjetUtilisateurs(Long idSujet, String idUser);

	List<DocumentProjetUtilisateurs> findAllDocumentProjetUtilisateurs(Long idSujet, String idUser);
}
