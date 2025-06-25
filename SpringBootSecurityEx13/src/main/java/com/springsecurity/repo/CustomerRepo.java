package com.springsecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

	  public Optional<Customer>findByEmail(String email);
	
	
	
}
