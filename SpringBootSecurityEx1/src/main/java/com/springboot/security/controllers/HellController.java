package com.springboot.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HellController {
	@GetMapping("/hello")
	public String hello() {
		return "hello from Spring Security";
	}

}
