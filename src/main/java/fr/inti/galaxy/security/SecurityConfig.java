package fr.inti.galaxy.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Value("${jwt.secret}")
	private String secret;
	
	//@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return new InMemoryUserDetailsManager(
				User.withUsername("user1").password(passwordEncoder.encode("12345")).authorities("USER").build(),
				User.withUsername("admin").password(passwordEncoder.encode("12345")).authorities("USER","ADMIN").build()
				);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**",
						"/v3/api-docs.yaml",
			            "/v3/api-docs/**",
			            "/swagger-ui/**",
			            "/swagger-ui.html","files/**").permitAll())
				.authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
				//.httpBasic(Customizer.withDefaults())
				.oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
				.build();
	}
	
	@Bean
	public JwtEncoder jwtEncoder() {
		//String secret = "jdnchfurhtjdnchfurhtjdnchfurhtjdnchfurhtjdnchfurhtjdnchfurht6598";
		return new NimbusJwtEncoder(new ImmutableSecret<>(secret.getBytes()));
	}
	
	
	
	@Bean
	public JwtDecoder jwtDecoder() {
		//String secret = "jdnchfurhtjdnchfurhtjdnchfurhtjdnchfurhtjdnchfurhtjdnchfurht6598";
		SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "RSA");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService UserDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(UserDetailsService);
		return new ProviderManager(daoAuthenticationProvider);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");
		///corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return   source;
	} 
	

}
