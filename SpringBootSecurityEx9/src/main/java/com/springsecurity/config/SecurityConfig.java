package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http.authorizeHttpRequests((requests)->requests
		    		.requestMatchers("/account","/balance").authenticated()
		    		.requestMatchers("/about","/contact","/auth/register").permitAll()
		    		);
		    
		  http.formLogin(Customizer.withDefaults());	 
		  http.httpBasic(Customizer.withDefaults());
		 http.csrf(csrf->csrf.disable());
		  return http.build();
		  
	}
	
	// using BCyptPasswordEncoder the password will save in hash form in db using bycrypt algorithm
	@Bean
        public PasswordEncoder passwordEncoder() {
        	return new BCryptPasswordEncoder();
        }
	
	
	
}
