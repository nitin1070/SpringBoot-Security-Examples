package in.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     private String email;
     private String pwd;
     private String role;
     private double balance;
	public Customer(String email, String pwd, String role, double balance) {
		
		this.email = email;
		this.pwd = pwd;
		this.role = role;
		this.balance = balance;
	}
	public Customer() {
		
	}
	
     
     
}
