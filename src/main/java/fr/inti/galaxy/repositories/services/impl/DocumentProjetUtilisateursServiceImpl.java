package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.DocumentProjetUtilisateurs;
import fr.inti.galaxy.repositories.DocumentProjetUtilisateursRepository;
import fr.inti.galaxy.repositories.services.DocumentProjetUtilisateursService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DocumentProjetUtilisateursServiceImpl implements DocumentProjetUtilisateursService {
	@Autowired
	private DocumentProjetUtilisateursRepository documentProjetUtilisateursRepositoryRepository;

	@Override
	public void affectSujetToUser(Long idSujet, String idUser) {
		documentProjetUtilisateursRepositoryRepository.affectSujetToUser(idSujet, idUser);
	}

	@Override
	public void deleteOneFromDocumentProjetUtilisateurs(Long idSujet, String idUser) {
		documentProjetUtilisateursRepositoryRepository.deleteOneFromDocumentProjetUtilisateurs(idSujet, idUser);

	}

	@Override
	public List<DocumentProjetUtilisateurs> findAllDocumentProjetUtilisateurs(Long idSujet, String idUser) {
		return documentProjetUtilisateursRepositoryRepository.findAllDocumentProjetUtilisateurs(idSujet, idUser);
	}

	@Override
	public List<DocumentProjetUtilisateurs> getAll() {
		return documentProjetUtilisateursRepositoryRepository.findAll();
	}
}
