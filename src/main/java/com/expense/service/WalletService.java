package com.expense.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expense.model.Wallet;
import com.expense.repository.WalletRepository;

@Service
public class WalletService {
	@Autowired
	WalletRepository walletrepository;
	
	public ResponseEntity<?> getById(Long id) {
		Wallet wallet = walletrepository.findByUserId(id);
		
       
            return new ResponseEntity<>(wallet,HttpStatus.OK);
       
    }

}
