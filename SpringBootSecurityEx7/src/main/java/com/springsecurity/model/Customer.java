package com.springsecurity.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Customer {
	private int id;
	private String email;
	private String pwd;
	private String role;

}
