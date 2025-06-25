package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	   @GetMapping("/account")
		public String getAccount() {
		   return "Account Details:[AccNo:12345]";
		}
	     @GetMapping("/balance")
	 	public String getBalance() {
	 		return "Balace is 50000.00";
	 	}
	     @GetMapping("/about")
	 	public String getAbout() {
	 		return "Welcome to Spring Secure Bank";
	 	}
	     @GetMapping("/contact")
	 	public String getContact() {
	 		return "Contact us at securebank@gmail.com";
	 	}
	     
	     @GetMapping("/reports")
		 	public String getReports() {
		 		return "Reports for financial year 2024-2025";
		 	}
}
