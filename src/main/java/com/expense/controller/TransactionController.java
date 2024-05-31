package com.expense.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.model.Transaction;
import com.expense.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{userId}")
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(userId, transaction);
        return ResponseEntity.ok(createdTransaction);
    }


    @GetMapping("/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }
}
