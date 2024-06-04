package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.model.User;
import com.expense.model.DTO.LoginRequestDTO;
import com.expense.model.DTO.LoginResponseDTO;
import com.expense.model.DTO.UserDTO;
import com.expense.security.JWTService;
import com.expense.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	JWTService jwtService;
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody UserDTO registerUserDto) {
		User registeredUser = authenticationService.signup(registerUserDto);

		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginRequestDTO loginRequest) {
		User authenticatedUser = authenticationService.authenticate(loginRequest);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponseDTO loginResponse = new LoginResponseDTO();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}

}
