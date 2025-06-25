package com.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.model.Customer;
import com.springsecurity.service.CustomerService;


@RestController
@RequestMapping("/auth")
public class CustomerController {
    @Autowired
	private CustomerService custServ;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
    	try {
    		String message=custServ.registerCustomer(customer);
    		return ResponseEntity.ok(message);
    	}catch(IllegalArgumentException ex) {
    		return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    	}
    }
}
