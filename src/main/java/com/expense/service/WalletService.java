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
	WalletRepository walletRepository;
	
	public ResponseEntity<?> getById(Long id) {
		Optional<Wallet> wallet = walletRepository.findById(id);
        if (!wallet.isPresent()) {
            return new ResponseEntity<>("ID NOT PRESENT ",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
    }

}
