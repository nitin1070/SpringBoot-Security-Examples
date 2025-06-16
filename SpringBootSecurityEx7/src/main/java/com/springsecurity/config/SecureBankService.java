package com.springsecurity.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Customer;
import com.springsecurity.repo.CustomerRepo;
@Service
public class SecureBankService implements UserDetailsService {
	@Autowired
        private CustomerRepo customerRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		        Customer cust = customerRepo.findByEmail(username).orElse(null);
		
		 
		   if(cust==null) {
			   throw new UsernameNotFoundException("User not found with usename "+username);
		   }
		   
		   List<GrantedAuthority>authorities=List.of(new SimpleGrantedAuthority(cust.getRole()));
			User user=new User(cust.getEmail(),cust.getPwd(),authorities);
			return user;
			
	}

}
