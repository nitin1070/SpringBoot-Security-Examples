package com.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http.authorizeHttpRequests((requests)->requests
		    		.requestMatchers("/account","/balance").authenticated()
		    		.requestMatchers("/about","/contact").permitAll()
		    		);
		    
		  http.formLogin(Customizer.withDefaults());	 
		  http.httpBasic(basic->basic.disable());
		  return http.build();
		  
	}
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		System.out.println("JdbcUserDetailsManager Bean created...");
		return new JdbcUserDetailsManager(dataSource);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
