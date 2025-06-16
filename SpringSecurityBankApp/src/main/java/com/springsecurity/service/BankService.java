package com.springsecurity.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Customer;
import com.springsecurity.model.Transaction;
import com.springsecurity.repo.CustomerRepository;
import com.springsecurity.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class BankService {

	@Autowired
	private TransactionRepository transRepo;
	@Autowired
	private CustomerRepository custRepo;

	@PreAuthorize("hasRole('USER')")
	public String getAccount() {
		String email = getLoggedInUserEmail();
		Customer cust = custRepo.findByEmail(email).get();
		return "Account Details:[Email:" + email + ",Balance:" + cust.getBalance() + ",AccNo:" + cust.getId() + "]";
	}

	@PreAuthorize("hasRole('USER')")
	public String getBalance() {
		String email = getLoggedInUserEmail();
		Customer cust = custRepo.findByEmail(email).get();
		return "Available balance:" + cust.getBalance();
	}

	@PreAuthorize("hasRole('ADMIN')")
	public String getReports() {
		return "Financial Reposrts for the year 2024-2025";
	}

	private String getLoggedInUserEmail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Transaction withdraw(double amt) {
		String email = getLoggedInUserEmail();
		Customer cust = custRepo.findByEmail(email).get();
		if(cust.getBalance()<amt) {
			throw new RuntimeException("Insifficient balance");
		}
		cust.setBalance(cust.getBalance()-amt);
		custRepo.save(cust);
		Transaction trans=new Transaction(email,amt,"WITHDRAW",LocalDateTime.now());
		return transRepo.save(trans);
	}
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Transaction deposit(double amt) {
		String email = getLoggedInUserEmail();
		Customer cust = custRepo.findByEmail(email).get();
		cust.setBalance(cust.getBalance()+amt);
		custRepo.save(cust);
		Transaction trans=new Transaction(email,amt,"DEPOSIT",LocalDateTime.now());
		return transRepo.save(trans);
	}
	@PostAuthorize("returnObject.isEmpty() or returnObject[0].email==authentication.name or hasRole('ADMIN')")
	public List<Transaction> getTransactions(String email){
		return transRepo.findByEmail(email);
	}
}
