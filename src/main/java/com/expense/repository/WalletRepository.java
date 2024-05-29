package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.model.Wallet;

public interface WalletRepository   extends JpaRepository<Wallet, Integer>{

}
