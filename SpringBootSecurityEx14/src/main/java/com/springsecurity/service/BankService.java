package com.springsecurity.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BankService {
	@PreAuthorize("hasRole('USER')")
	public String getAccount() {
		String email=getLoggedInUserEmail();
		if(email.endsWith("@securebank.com"))
			return "Account Details:[Name:Manish,AccNo:12345]";
		
		return "Access Denied: Unauthorized email domain";
	}
	@PreAuthorize("hasRole('USER')")
	public String getBalance() {
		return "Available balance:Rs50000.0";
	}
	@PreAuthorize("hasRole('ADMIN')")
	public String getReports() {
		return "Financial Reposrts for the year 2024-2025";
	}
	
	private String getLoggedInUserEmail() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}


}
