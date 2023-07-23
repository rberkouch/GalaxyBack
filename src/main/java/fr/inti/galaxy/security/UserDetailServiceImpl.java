package fr.inti.galaxy.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.repositories.services.impl.UtilisateurServiceImpl;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private UtilisateurServiceImpl accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur appUser = accountService.loadUserByUsername(username);
	//String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
	List<SimpleGrantedAuthority> authorities =	appUser.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
		if (appUser==null) throw new UsernameNotFoundException(String.format("User %s not found",username ));
		UserDetails userDetails=User.withUsername(appUser.getUsername())
									.password(appUser.getPassword())
									.authorities(authorities)
									//.roles(roles)
									.build();
		return userDetails;
	}

}
