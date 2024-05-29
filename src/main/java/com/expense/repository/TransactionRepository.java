package com.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Long userId);
}
