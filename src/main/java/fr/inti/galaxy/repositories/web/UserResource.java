package fr.inti.galaxy.repositories.web;

import static fr.inti.galaxy.constants.FileConstant.FORWARD_SLASH;
import static fr.inti.galaxy.constants.FileConstant.TEMP_PROFILE_IMAGE_BASE_URL;
import static fr.inti.galaxy.constants.FileConstant.USER_FOLDER;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.exceptions.EmailExistException;
import fr.inti.galaxy.exceptions.NotAnImageFileException;
import fr.inti.galaxy.exceptions.UtilisateurNotFoundException;
import fr.inti.galaxy.exceptions.UtilisateurNameExistException;
import fr.inti.galaxy.repositories.services.impl.UtilisateurServiceImpl;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping(path = { "/", "/user" })
public class UserResource {
	public static final String EMAIL_SENT = "An email with a new password was sent to: ";
	public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	private AuthenticationManager authenticationManager;
	private UtilisateurServiceImpl userServiceImpl;
	
	/* @Autowired
	private JavaMailSender emailSender;
	 
	 @Autowired
	 private TemplateEngine templateEngine;
	 
	 
	 @GetMapping("/mailTest")
		public void mailTest() {
		 
		 SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("intiformationintiformaion@gmail.com");
	        message.setTo("dardour.mohammed@gmail.com"); 
	        message.setSubject("objet test"); 
	        message.setText("test de test");
	        emailSender.send(message);
		 
		 try {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            Context context = new Context();
	            context.setVariable("password", "password");

	            // Charger le contenu HTML à partir du fichier
	            String emailContent = templateEngine.process("registration-email", context);
	            
	            helper.setTo("dardour.mohammed@gmail.com");
	            helper.setSubject("Confirmation de compte");
	            helper.setText(emailContent, true);

	            emailSender.send(message);
	        } catch (Exception e) {
	            // Gérer les erreurs d'envoi d'e-mail
	            e.printStackTrace();
	        }


		}*/
	
	@Autowired
	public UserResource(AuthenticationManager authenticationManager, UtilisateurServiceImpl userServiceImpl) {
		this.authenticationManager = authenticationManager;
		this.userServiceImpl = userServiceImpl;

	}

	@PostMapping("/register")
	public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur user)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException {
		Utilisateur newUser = userServiceImpl.register(user.getFirstName(), user.getLastName(), user.getUsername(),
				user.getEmail());
		return new ResponseEntity<>(newUser, OK);
	}

	// @PostMapping("/add")
//    public ResponseEntity<Utilisateur> addNewUser(@RequestParam("firstName") String firstName,
//                                           @RequestParam("lastName") String lastName,
//                                           @RequestParam("username") String username,
//                                           @RequestParam("email") String email,
//                                           @RequestParam("role") String role,
//                                           @RequestParam("isActive") String isActive,
//                                           @RequestParam("isNonLocked") String isNonLocked,
//                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws UtilisateurNotFoundException, UtilisateurnameExistException, EmailExistException, IOException, NotAnImageFileException {
//    	Utilisateur newUser = userServiceImpl.addNewUtilisateur(firstName, lastName, username,email, role, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
//        return new ResponseEntity<>(newUser, OK);
//    }

	@PostMapping("/add")
	public Utilisateur addNewUser(@RequestBody Utilisateur user) throws UtilisateurNotFoundException,
			UtilisateurNameExistException, EmailExistException, IOException, NotAnImageFileException {
		// Utilisateur newUser = userServiceImpl.addNewUser(username, password,
		// email,confirPassword);
		//System.out.println(user.getProfile().getId());
		return userServiceImpl.addNewUser(user);
	}

	@PostMapping("/update")
	public ResponseEntity<Utilisateur> update(@RequestParam("currentUsername") String currentUsername,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("role") String role, @RequestParam("isActive") String isActive,
			@RequestParam("isNonLocked") String isNonLocked,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImage)
			throws UtilisateurNotFoundException, UtilisateurNameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		Utilisateur updatedUser = userServiceImpl.updateUtilisateur(currentUsername, firstName, lastName, username,
				email, role, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
		return new ResponseEntity<>(updatedUser, OK);
	}

	@GetMapping("/find/{username}")
	public Utilisateur getUser(@PathVariable("username") String username) {
		Utilisateur user = userServiceImpl.findUtilisateurByUtilisateurname(username);
		System.out.println(user);
		return user;
	}

	@GetMapping("/list")
	public ResponseEntity<List<Utilisateur>> getAllUsers() {
		List<Utilisateur> users = userServiceImpl.getUtilisateurs();
		return new ResponseEntity<>(users, OK);
	}

	@PostMapping("/updateProfileImage")
	public ResponseEntity<Utilisateur> updateProfileImage(@RequestParam("username") String username,
			@RequestParam(value = "profileImage") MultipartFile profileImage) throws UtilisateurNotFoundException,
			UtilisateurNameExistException, EmailExistException, IOException, NotAnImageFileException {
		Utilisateur user = userServiceImpl.updateProfileImage(username, profileImage);
		return new ResponseEntity<>(user, OK);
	}

	@GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName)
			throws IOException {
		return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
	}

	@GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
	public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
		URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try (InputStream inputStream = url.openStream()) {
			int bytesRead;
			byte[] chunk = new byte[1024];
			while ((bytesRead = inputStream.read(chunk)) > 0) {
				byteArrayOutputStream.write(chunk, 0, bytesRead);
			}
		}
		return byteArrayOutputStream.toByteArray();
	}

	@GetMapping("/user/search")
	// @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
	public List<Utilisateur> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		return userServiceImpl.searchUtilisateurs("%" + keyword + "%");
	}

	@GetMapping("/user/ifroleapprenant/search")
	public List<Utilisateur> findUsersIfRoleIsApprenant(@RequestParam(name = "keyword", defaultValue = "") String lastname) {
		return userServiceImpl.findUsersIfRoleIsApprenant("%" + lastname + "%");
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	
	@GetMapping("/userWithDocumentSujet/{idSujet}")
	public List<Utilisateur> findByDocumentSujet(@PathVariable("idSujet")int idSujet)
	{
		return userServiceImpl.findUsersWithSujetDocumentProjet(idSujet);
	}
	
	@PostMapping("/updatePassword")
	public void updatePassword(String username,String password)
	{
		System.out.println(username);
		System.out.println(password);
		Utilisateur utilisateur=userServiceImpl.findUtilisateurByUtilisateurname(username);
		utilisateur.setPassword(password);
		utilisateur.setFirstLogin(true);
		userServiceImpl.addNewUser(utilisateur);
		
	}
}
