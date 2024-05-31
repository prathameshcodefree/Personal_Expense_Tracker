package com.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.TokenLog;
import com.expense.model.constant.UserType;


@Repository
public interface TokenLogRepository extends JpaRepository<TokenLog , Integer> {

	Optional<TokenLog> findByToken(String token);
	
	

	






}
