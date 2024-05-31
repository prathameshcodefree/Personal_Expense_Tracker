package com.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.model.Transaction;
import com.expense.model.User;
import com.expense.model.Wallet;
import com.expense.model.constant.TransactionType;
import com.expense.repository.TransactionRepository;
import com.expense.repository.UserRepository;
import com.expense.repository.WalletRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WalletRepository walletRepository;

	 public Transaction createTransaction(Long userId, Transaction transaction) {
	        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	        Wallet wallet = user.getWallet();
	        if (wallet == null) {
	            wallet = new Wallet();
	            wallet.setUser(user);
	            wallet.setBalance(0.0); // Initialize balance if wallet is null
	            user.setWallet(wallet);
	        }

	        double newBalance;
	        if (transaction.getType() == TransactionType.INCOME) {
	            newBalance = wallet.getBalance() + transaction.getAmount();
	        } else if (transaction.getType() == TransactionType.EXPENSE) {
	            newBalance = wallet.getBalance() - transaction.getAmount();
	        } else {
	            throw new RuntimeException("Invalid transaction type");
	        }

	        wallet.setBalance(newBalance);
	        transaction.setUser(user);
	        return transactionRepository.save(transaction);
	    }
		
	
		public List<Transaction> getTransactionsByUser(Long userId) {
			return transactionRepository.findByUserId(userId);
		}
		public List<Transaction> getAllTransactions() {
			return transactionRepository.findAll();
		}
}
