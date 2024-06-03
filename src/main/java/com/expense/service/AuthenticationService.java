package com.expense.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.model.DTO.LoginRequestDTO;
import com.expense.model.DTO.UserDTO;
import com.expense.repository.UserRepository;
import com.task.DTO.LoginUserDto;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(UserDTO input) {

		User user = modelMapper.map(input, User.class);

		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return userRepository.save(user);
	}

	public User authenticate(LoginRequestDTO loginRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()

				));

		return userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
	}

}
