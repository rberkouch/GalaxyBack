package fr.inti.galaxy.repositories.services.impl;

import static fr.inti.galaxy.constants.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static fr.inti.galaxy.constants.FileConstant.DIRECTORY_CREATED;
import static fr.inti.galaxy.constants.FileConstant.DOT;
import static fr.inti.galaxy.constants.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static fr.inti.galaxy.constants.FileConstant.FORWARD_SLASH;
import static fr.inti.galaxy.constants.FileConstant.JPG_EXTENSION;
import static fr.inti.galaxy.constants.FileConstant.NOT_AN_IMAGE_FILE;
import static fr.inti.galaxy.constants.FileConstant.USER_FOLDER;
import static fr.inti.galaxy.constants.FileConstant.USER_IMAGE_PATH;
import static fr.inti.galaxy.enums.Role.ROLE_USER;
import static fr.inti.galaxy.constants.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static fr.inti.galaxy.constants.UserImplConstant.NO_USER_FOUND_BY_EMAIL;
import static fr.inti.galaxy.constants.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static fr.inti.galaxy.constants.UserImplConstant.USERNAME_ALREADY_EXISTS;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.enums.Role;
import fr.inti.galaxy.exceptions.EmailExistException;
import fr.inti.galaxy.exceptions.EmailNotFoundException;
import fr.inti.galaxy.exceptions.NotAnImageFileException;
import fr.inti.galaxy.exceptions.UtilisateurNotFoundException;
import fr.inti.galaxy.exceptions.UtilisateurNameExistException;
import fr.inti.galaxy.repositories.SujetRepository;
import fr.inti.galaxy.repositories.UtilisateurRepository;
import fr.inti.galaxy.repositories.services.UtilisateurService;
import fr.inti.galaxy.security.AppRole;
import fr.inti.galaxy.security.AppRoleRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Qualifier("userDetailsService")
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private SujetRepository sujetRepository;
	
	@Autowired
	private JavaMailSender emailSender;
		 
	@Autowired
	private TemplateEngine templateEngine;

//	@Autowired
//	public UtilisateurServiceImpl(UtilisateurRepository userRepository, PasswordEncoder passwordEncoder,) {
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//
//	}

	public Utilisateur save(Utilisateur user) {
		return userRepository.save(user);
	}

	@Override
	public Utilisateur register(String firstName, String lastName, String username, String email)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException {
		// validateNewUsernameAndEmail(EMPTY, username, email);
		Utilisateur user = new Utilisateur();
		user.setUserId(generateUserId());
		String password = generatePassword();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmail(email);
		// user.setJoinDate(new Date());
		user.setPassword(encodePassword(password));
		user.setActive(true);
		// user.setNotLocked(true);
		// user.setRole(ROLE_USER.name());
		// user.setAuthorities(ROLE_USER.getAuthorities());
		user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
		userRepository.save(user);

		return user;
	}

	@Override
	public Utilisateur addNewUtilisateur(String firstName, String lastName, String username, String email, String role,
			boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UtilisateurNotFoundException,
			UtilisateurNameExistException, EmailExistException, IOException, NotAnImageFileException {
		// validateNewUsernameAndEmail(EMPTY, username, email);
		Utilisateur user = new Utilisateur();
		String password = generatePassword();
		user.setUserId(generateUserId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		// user.setJoinDate(new Date());
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(encodePassword(password));
		user.setActive(isActive);
		// user.setNotLocked(isNonLocked);
		// user.setRole(getRoleEnumName(role).name());
		// user.setAuthorities(getRoleEnumName(role).getAuthorities());
		user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
		userRepository.save(user);
		saveProfileImage(user, profileImage);

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(String currentUsername, String newFirstName, String newLastName,
			String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive,
			MultipartFile profileImage) throws UtilisateurNotFoundException, UtilisateurNameExistException,
			EmailExistException, IOException, NotAnImageFileException {
		Utilisateur currentUser = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);
		currentUser.setFirstName(newFirstName);
		currentUser.setLastName(newLastName);
		currentUser.setUsername(newUsername);
		currentUser.setEmail(newEmail);
		currentUser.setActive(isActive);
		// currentUser.setNotLocked(isNonLocked);
		// urrentUser.setRole(getRoleEnumName(role).name());
		// currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
		userRepository.save(currentUser);
		saveProfileImage(currentUser, profileImage);
		return currentUser;
	}

	@Override
	public void resetPassword(String email) throws EmailNotFoundException {
		Utilisateur user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
		}
		String password = generatePassword();
		user.setPassword(encodePassword(password));
		userRepository.save(user);

	}

	@Override
	public Utilisateur updateProfileImage(String username, MultipartFile profileImage)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		Utilisateur user = validateNewUsernameAndEmail(username, null, null);
		saveProfileImage(user, profileImage);
		return user;
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		return userRepository.findAll();
	}

	@Override
	public Utilisateur findUtilisateurByUtilisateurname(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Utilisateur findUtilisateurByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public void deleteUtilisateur(String username) throws IOException {
		System.out.println("username="+username);
		Utilisateur user = userRepository.findByUsername(username);
		//user.setDocumentProjets(null);
		System.out.println("user="+user.getUsername());
		/*Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
		FileUtils.deleteDirectory(new File(userFolder.toString()));*/
		userRepository.delete(user);
	}

	@Override
	public void saveProfileImage(Utilisateur user, MultipartFile profileImage)
			throws IOException, NotAnImageFileException {
		if (profileImage != null) {
			if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE)
					.contains(profileImage.getContentType())) {
				throw new NotAnImageFileException(profileImage.getOriginalFilename() + NOT_AN_IMAGE_FILE);
			}
			Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
			if (!Files.exists(userFolder)) {
				Files.createDirectories(userFolder);

			}
			Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
			Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION),
					REPLACE_EXISTING);
			user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
			userRepository.save(user);

		}
	}

	@Override
	public String setProfileImageUrl(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
	}

	@Override
	public Role getRoleEnumName(String role) {
		return Role.valueOf(role.toUpperCase());
	}

	@Override
	public String getTemporaryProfileImageUrl(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username)
				.toUriString();
	}

	@Override
	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	@Override
	public String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	@Override
	public List<Utilisateur> searchUtilisateurs(String keyword) {
		List<Utilisateur> customers = userRepository.searchUser(keyword);
		// List<Utilisateur> customerDTOS = customers.stream().map(cust ->
		// dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
		return customers;
	}

	@Override
	public Utilisateur addNewUtilisateur(Utilisateur user) throws UtilisateurNotFoundException,
			UtilisateurNameExistException, EmailExistException, IOException, NotAnImageFileException {

		userRepository.save(user);

		return user;
	}

	@Override
	public Utilisateur validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException {
		Utilisateur userByNewUsername = findUtilisateurByUtilisateurname(newUsername);
		Utilisateur userByNewEmail = findUtilisateurByEmail(newEmail);
		if (StringUtils.isNotBlank(currentUsername)) {
			Utilisateur currentUser = findUtilisateurByUtilisateurname(currentUsername);
			if (currentUser == null) {
				throw new UtilisateurNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}
			if (userByNewUsername != null && !currentUser.getUserId().equals(userByNewUsername.getUserId())) {
				throw new UtilisateurNameExistException(USERNAME_ALREADY_EXISTS);
			}
			if (userByNewEmail != null && !currentUser.getUserId().equals(userByNewEmail.getUserId())) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} else {
			if (userByNewUsername != null) {
				throw new UtilisateurNameExistException(USERNAME_ALREADY_EXISTS);
			}
			if (userByNewEmail != null) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

	@Override
	public Utilisateur addNewUser(String username, String password, String email, String confirPassword) {
		Utilisateur appUser = userRepository.findByUsername(username);
		if (appUser != null)
			throw new RuntimeException("This user already exists");
		if (!password.equals(confirPassword))
			throw new RuntimeException("Password not match");
		appUser = Utilisateur.builder().userId(UUID.randomUUID().toString()).username(username)
				.password(passwordEncoder.encode(password)).email(email).build();

		Utilisateur savedAppUser = userRepository.save(appUser);
		return savedAppUser;
	}

	@Override
	public AppRole addnewRole(String role) {
		AppRole appRole = appRoleRepository.findById(role).orElse(null);
		if (appRole != null)
			throw new RuntimeException("This role already exist");
		appRole = AppRole.builder().role(role).build();
		return appRoleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String role) {
		Utilisateur appUser = userRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findById(role).get();
		appUser.getRoles().add(appRole);
		// appUserRepository.save(appUser); pas besoin car transactional en haut dès
		// qu'il quitte il fait commit()
	}

	@Override
	public void removeRoleFromUser(String username, String role) {
		Utilisateur appUser = userRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findById(role).get();
		appUser.getRoles().remove(appRole);

	}

	@Override
	public Utilisateur loadUserByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	public Utilisateur addNewUser(Utilisateur user) {
		if (user.getUserId() == null) {
			user.setUserId(UUID.randomUUID().toString());
		}
		
		String pass=user.getPassword();

		if (user.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		 Utilisateur savedUser = userRepository.save(user);
		 	if(savedUser.isFirstLogin()==false)
		 	{
		 		 sendMail(pass, user.getEmail());
		 	}
		   

		    return savedUser;

		// TODO Auto-generated method stub
		//return userRepository.save(user);
	}
	
	public void sendMail(String password,String email)
	{
		//-------------------------------------------------------------------------------------
		 try {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            Context context = new Context();
	            context.setVariable("password", password);

	            // Charger le contenu HTML à partir du fichier
	            String emailContent = templateEngine.process("registration-email", context);
	            
	            helper.setTo(email);
	            helper.setSubject("Création de compte");
	            helper.setText(emailContent, true);

	            emailSender.send(message);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		
	}

	@Override
	public List<Utilisateur> findUsersIfRoleIsApprenant(String lastname) {
		return userRepository.findUsersIfRoleIsApprenant(lastname);
	}
	
	public List<Utilisateur> findUsersWithSujetDocumentProjet(int id)
	{
		return sujetRepository.chercherUserWithSujet(id);
	}

}
