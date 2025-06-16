package com.springsecurity.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springsecurity.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	List<Transaction>findByEmail(String email);

}
