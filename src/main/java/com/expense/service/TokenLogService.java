package com.expense.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.model.TokenLog;
import com.expense.repository.TokenLogRepository;


@Service
public class TokenLogService {

	@Autowired
	TokenLogRepository tokenLogRepository;

	


	// invalidate token
	public boolean invalidateToken(String token) {
		Optional<TokenLog> tokenLogOptional = tokenLogRepository.findByToken(token);

		if (tokenLogOptional.isPresent()) {
			TokenLog log = tokenLogOptional.get();
			log.setValid(false);
			log.setExpiryTime(LocalDateTime.now().plusMinutes(1));
			log.setLogoutTime(LocalDateTime.now());
			tokenLogRepository.save(log);
			return true;
		}
		return false;
	}

	
	
}
