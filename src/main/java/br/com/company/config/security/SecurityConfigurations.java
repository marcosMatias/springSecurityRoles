package br.com.company.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

private final SecurityFilter securityFilter;
	
	public SecurityConfigurations(SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;

	}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		
		return http.csrf(csr -> csr.disable())
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .authorizeHttpRequests(authorize -> { authorize
														.requestMatchers(HttpMethod.POST,"/api/v1/auth").permitAll()	
														.requestMatchers("/actuator", "/actuator/**", "/v3/api-docs/**", "/api-docs/**","/swagger-ui.html", "/swagger-ui/**").permitAll()														
														.anyRequest().authenticated();																												
														})
				
				 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)					
				 .build();
			
	   	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}


    @Bean
    PasswordEncoder passwordEncoder() {
		
		return  new BCryptPasswordEncoder();
	}
	
	
	
	

	
}
