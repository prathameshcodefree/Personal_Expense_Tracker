package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.service.WalletService;

@RestController
@RequestMapping("/api")
public class WalletController {
	
	@Autowired
	WalletService walletService;
	
    @GetMapping("/wallets/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
     return walletService.getById(id);
    }
}
