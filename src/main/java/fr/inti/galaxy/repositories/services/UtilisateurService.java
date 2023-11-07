package fr.inti.galaxy.repositories.services;

import static fr.inti.galaxy.constants.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static fr.inti.galaxy.constants.FileConstant.DOT;
import static fr.inti.galaxy.constants.FileConstant.FORWARD_SLASH;
import static fr.inti.galaxy.constants.FileConstant.JPG_EXTENSION;
import static fr.inti.galaxy.constants.FileConstant.USER_IMAGE_PATH;
import static fr.inti.galaxy.constants.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static fr.inti.galaxy.constants.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static fr.inti.galaxy.constants.UserImplConstant.USERNAME_ALREADY_EXISTS;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.enums.Role;
import fr.inti.galaxy.exceptions.EmailExistException;
import fr.inti.galaxy.exceptions.EmailNotFoundException;
import fr.inti.galaxy.exceptions.NotAnImageFileException;
import fr.inti.galaxy.exceptions.UtilisateurNotFoundException;
import fr.inti.galaxy.exceptions.UtilisateurNameExistException;
import fr.inti.galaxy.security.AppRole;

public interface UtilisateurService {
	Utilisateur register(String firstName, String lastName, String Utilisateurname, String email)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException;

	List<Utilisateur> getUtilisateurs();

	Utilisateur findUtilisateurByUtilisateurname(String Utilisateurname);

	Utilisateur addNewUtilisateur(String firstName, String lastName, String Utilisateurname, String email, String role,
			boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UtilisateurNotFoundException,
			UtilisateurNameExistException, EmailExistException, IOException, NotAnImageFileException;

	Utilisateur addNewUtilisateur(Utilisateur user) throws UtilisateurNotFoundException, UtilisateurNameExistException,
			EmailExistException, IOException, NotAnImageFileException;

	Utilisateur updateUtilisateur(String currentUtilisateurname, String newFirstName, String newLastName,
			String newUtilisateurname, String newEmail, String role, boolean isNonLocked, boolean isActive,
			MultipartFile profileImage) throws UtilisateurNotFoundException, UtilisateurNameExistException,
			EmailExistException, IOException, NotAnImageFileException;

	void resetPassword(String email) throws EmailNotFoundException;

	void saveProfileImage(Utilisateur user, MultipartFile profileImage) throws IOException, NotAnImageFileException;

	Utilisateur updateProfileImage(String Utilisateurname, MultipartFile profileImage)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException, IOException,
			NotAnImageFileException;

	List<Utilisateur> searchUtilisateurs(String keyword);

	String setProfileImageUrl(String username);

	Role getRoleEnumName(String role);

	String getTemporaryProfileImageUrl(String username);

	String encodePassword(String password);

	String generatePassword();

	String generateUserId();

	Utilisateur validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException;

	Utilisateur findUtilisateurByEmail(String email);

	void deleteUtilisateur(String username) throws IOException;

	Utilisateur addNewUser(String username, String password, String email, String confirPassword);

	AppRole addnewRole(String role);

	void addRoleToUser(String username, String role);

	void removeRoleFromUser(String username, String role);

	Utilisateur loadUserByUsername(String username);

	List<Utilisateur> findUsersIfRoleIsApprenant(String lastname);
	
	List<Utilisateur> findUsersWithSujetDocumentProjet(int id);
	
	

}
