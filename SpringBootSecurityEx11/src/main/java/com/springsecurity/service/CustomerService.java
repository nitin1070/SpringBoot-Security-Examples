package com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.springsecurity.model.Customer;
import com.springsecurity.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String register(Customer customer){
			if(customerRepo.findByEmail(customer.getEmail()).isPresent()) {
				throw new IllegalArgumentException("User already registered with email id "+customer.getEmail());
			}
			customer.setPwd(passwordEncoder.encode(customer.getPwd()));
			customerRepo.save(customer);	
		return "Registration successfully with email" + customer.getEmail();
		
	}

	public String registerPlain(Customer customer){
		if(customerRepo.findByEmail(customer.getEmail()).isPresent()) {
			throw new IllegalArgumentException("User already registered with email id "+customer.getEmail());
		}
		customer.setPwd("{noop}"+customer.getPwd());
		customerRepo.save(customer);	
	return "Registration successfully with email" + customer.getEmail();
	
}

}
