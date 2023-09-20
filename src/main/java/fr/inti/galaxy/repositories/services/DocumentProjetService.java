package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.DocumentProjet;

public interface DocumentProjetService {
	public List<DocumentProjet> getAll();

	public DocumentProjet getDocumentProjetById(Integer id);

	public DocumentProjet save(DocumentProjet DocumentProjet);
	
	public void delete(int id);
}
