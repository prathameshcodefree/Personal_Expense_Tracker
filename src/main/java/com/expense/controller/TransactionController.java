package com.expense.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.model.Transaction;
import com.expense.model.DTO.LoginResponseDTO;
import com.expense.security.JWTService;
import com.expense.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    JWTService jwtservice;
    
    

    @PostMapping("/{userId}")
    
    public ResponseEntity<Transaction> createTransactionC( @RequestBody   LoginResponseDTO loginresdto , @RequestBody Transaction transaction) {
        
    	UserDetails user = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	
    	
    	Transaction createdTransaction = transactionService.createTransaction(jwtservice.extractUserId(loginresdto.getToken())	, transaction);
        return ResponseEntity.ok(createdTransaction);
    }


    @GetMapping("/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }
}
