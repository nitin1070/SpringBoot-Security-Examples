package com.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.service.BankService;


@RestController
public class BankController {
    @Autowired
	private BankService bserv;
	@GetMapping("/account")
	public String getAccount() {
		return bserv.getAccount();
	}
	@GetMapping("/balance")
	public String getBalance() {
		return bserv.getBalance();
	}
	@GetMapping("/about")
	public String getAbout() {
		return "Welcome To Secure Bank";
	}
	@GetMapping("/contact")
	public String getContact() {
		return "Contact us at:support@securebank.com";
	}
	@GetMapping("/reports")
	public String getReports() {
		return bserv.getReports();
	}
	
}
