package com.springsecurity.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BankService {
	@PreAuthorize("hasRole('USER')")
	public String getAccount() {
		return "Account Details:[Name:Manish,AccNo:12345]";
	}
	@PreAuthorize("hasRole('USER')")
	public String getBalance() {
		return "Available balance:Rs50000.0";
	}
	@PreAuthorize("hasRole('ADMIN')")
	public String getReports() {
		return "Financial Reposrts for the year 2024-2025";
	}

}
