package fr.inti.galaxy.security;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.repositories.services.impl.UtilisateurServiceImpl;
@RestController
@RequestMapping("/auth")
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UtilisateurServiceImpl accountService;
	
	@Autowired
	private JwtEncoder JwtEncoder;
	
	@Autowired
	private UtilisateurServiceImpl utilisateurServiceImpl;
	
	@GetMapping("/profile")
	public Authentication authentication (Authentication authentication) {
		return authentication;
	}
	
	@PostMapping("/login")
	public Map<String,String> login(String Username, String password) {
	Authentication authentication	=  authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(Username, password)
			);
	Instant instant = Instant.now();
	String scope = authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
	JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
			.issuedAt(instant)
			.expiresAt(instant.plus(50,ChronoUnit.MINUTES))
			.subject(Username)
			.claim("scope", scope)
			.build();
	JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
			JwsHeader.with(MacAlgorithm.HS512).build(),
			jwtClaimsSet
			);
	String jwt = JwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	Utilisateur appUser = accountService.loadUserByUsername(Username);
	boolean firstLogin =appUser.isFirstLogin();
	String firstLoginString=""+0;
	if(firstLogin)
		firstLoginString=""+1;
	return Map.of("access-token",jwt,"firstLogin",firstLoginString);
	
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<Utilisateur> login(String Username, String password) {
//	Authentication authentication	=  authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(Username, password)
//			);
//	Instant instant = Instant.now();
//	String scope = authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
//	JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//			.issuedAt(instant)
//			.expiresAt(instant.plus(10,ChronoUnit.MINUTES))
//			.subject(Username)
//			.claim("scope", scope)
//			.build();
//	JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
//			JwsHeader.with(MacAlgorithm.HS512).build(),
//			jwtClaimsSet
//			);
//	String jwt = JwtEncoder.encode(jwtEncoderParameters).getTokenValue();
//	Utilisateur loginUser = utilisateurServiceImpl.findUtilisateurByUtilisateurname(Username);
//	//return ;
//	Map<String,String> mapppp = Map.of("access-token",jwt);
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("access-token", jwt);
//	 return new ResponseEntity<>(loginUser,headers ,OK);
//	}
	
//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody User user) {
//        authenticate(user.getUsername(), user.getPassword());
//        User loginUser = userService.findUserByUsername(user.getUsername());
//        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
//        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
//        return new ResponseEntity<>(loginUser, jwtHeader, OK);
//    }
}
