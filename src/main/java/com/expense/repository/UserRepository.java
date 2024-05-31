package com.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	

	Optional<User> findUserByUserName(String userName);
	Optional<User> findByEmail(String email);

}
