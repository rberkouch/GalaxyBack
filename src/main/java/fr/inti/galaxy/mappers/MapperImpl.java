package fr.inti.galaxy.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.dtos.DocumentDTO;
import fr.inti.galaxy.dtos.FormationDTO;
import fr.inti.galaxy.dtos.ModuleFormationDTO;
import fr.inti.galaxy.dtos.UtilisateurDTO;
import fr.inti.galaxy.entities.Document;
import fr.inti.galaxy.entities.Formation;
import fr.inti.galaxy.entities.ModuleFormation;
import fr.inti.galaxy.entities.Utilisateur;

@Service
public class MapperImpl {

	public static DocumentDTO fromDocument(Document document) {
		DocumentDTO documentDTO = new DocumentDTO();
		BeanUtils.copyProperties(document, documentDTO);
		documentDTO.setUtilisateurs(document.getUtilisateurs().stream().map(MapperImpl::fromUtilisateur).collect(Collectors.toList()));
		return documentDTO;
	}
	
	public static Document fromDocumentDTO(DocumentDTO documentDTO) {
		Document document = new Document();
		BeanUtils.copyProperties(documentDTO,document);
		document.setUtilisateurs(documentDTO.getUtilisateurs().stream().map(MapperImpl::fromUtilisateurDTO).collect(Collectors.toList()));
		return document;
	}
	
	public static FormationDTO fromFormation(Formation formation) {
		FormationDTO formationDTO = new FormationDTO();
		BeanUtils.copyProperties(formation, formationDTO);
		formationDTO.setModules(formation.getModules().stream().map(MapperImpl::fromModuleFormation).collect(Collectors.toList()));
		return formationDTO;
	}
	
	public static Formation fromFormationDTO(FormationDTO formationDTO) {
		Formation formation = new Formation();
		BeanUtils.copyProperties(formationDTO,formation);
		formation.setModules(formationDTO.getModules().stream().map(MapperImpl::fromModuleFormationDTO).collect(Collectors.toList()));
		return formation;
	}
	
	public static ModuleFormationDTO fromModuleFormation(ModuleFormation moduleFormation) {
		ModuleFormationDTO moduleFormationDTO = new ModuleFormationDTO();
		BeanUtils.copyProperties(moduleFormation, moduleFormationDTO);
		moduleFormationDTO.setFormations(moduleFormation.getFormations().stream().map(MapperImpl::fromFormation).collect(Collectors.toList()));
		moduleFormationDTO.setDocuments(moduleFormation.getDocuments().stream().map(MapperImpl::fromDocument).collect(Collectors.toList()));
		return moduleFormationDTO;
	}
	
	public static ModuleFormation fromModuleFormationDTO(ModuleFormationDTO moduleFormationDTO) {
		ModuleFormation moduleFormation = new ModuleFormation();
		BeanUtils.copyProperties(moduleFormationDTO,moduleFormation);
		moduleFormation.setFormations(moduleFormationDTO.getFormations().stream().map(MapperImpl::fromFormationDTO).collect(Collectors.toList()));
		moduleFormation.setDocuments(moduleFormationDTO.getDocuments().stream().map(MapperImpl::fromDocumentDTO).collect(Collectors.toList()));
		return moduleFormation;
	}
	
	public static UtilisateurDTO fromUtilisateur(Utilisateur utilisateur) {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		BeanUtils.copyProperties(utilisateur, utilisateurDTO);
		utilisateurDTO.setDocuments(utilisateur.getDocuments().stream().map(MapperImpl::fromDocument).collect(Collectors.toList()));
		return utilisateurDTO;
	}

	public static Utilisateur fromUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(utilisateurDTO, utilisateur);
		utilisateur.setDocuments(utilisateurDTO.getDocuments().stream().map(MapperImpl::fromDocumentDTO).collect(Collectors.toList()));
		return utilisateur;
	}
}
