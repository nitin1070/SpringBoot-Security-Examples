package com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Customer;
import com.springsecurity.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo repo;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public String registerCustomer(Customer customer) {
    	if(repo.findByEmail(customer.getEmail()).isPresent()) {
    		throw new IllegalArgumentException("User already exists with email:"+customer.getEmail());
    	}
    	customer.setPwd(encoder.encode(customer.getPwd()));
    	repo.save(customer);
    	return "Registration successful for :"+customer.getEmail();
    }

}
