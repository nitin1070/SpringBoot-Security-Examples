package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1= User.withUsername("nitin").password("{noop}nitin123").build(); // noop stands for NoOperation which is helps to indicate that we r not send enccoded password
		UserDetails user2= User.withUsername("ajay").password("{noop}ajay123").build();  
		return new InMemoryUserDetailsManager(user1,user2);
	}

}
