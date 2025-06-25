package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	@GetMapping("/account")
	public String getAccount() {
		return "Account Details:[Name:Manish,AccNo:12345]";
	}
	@GetMapping("/balance")
	public String getBalance() {
		return "Available balance:Rs50000.0";
	}
	@GetMapping("/about")
	public String getAbout() {
		return "Welcome To Secure Bank";
	}
	@GetMapping("/contact")
	public String getContact() {
		return "Contact us at:support@securebank.com";
	}
}
