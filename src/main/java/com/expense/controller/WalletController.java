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
	
    @GetMapping("/wallet/{userId}")
    public ResponseEntity<?> getWalletByUserId(@PathVariable Long userId) {
        return walletService.getById(userId);
    }
 
}
