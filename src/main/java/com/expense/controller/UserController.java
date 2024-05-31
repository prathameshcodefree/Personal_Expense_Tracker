package com.expense.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.model.User;
import com.expense.model.DTO.UserDTO;
import com.expense.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/user")
	public ResponseEntity<?> getAllUser() {
		
		
		
		List<UserDTO> users = userService.getAllUser();
		if (users != null) {
			return new ResponseEntity<>(users,HttpStatus.OK);
		}

		return new ResponseEntity<>("NO DATA IN USER LIST", HttpStatus.NOT_FOUND);

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable Long id) {
		UserDTO user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	


	@PostMapping("/user/signup")
	public Map<String, String> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return errors;
		}
		UserDTO createdUser = userService.createUser(userDTO);
		Map<String, String> response = new HashMap<>();
		response.put("message", "User created successfully");
		response.put("userId", createdUser.getId().toString());
		return response;
	}

	@PutMapping("/user/{id}")
	public Map<String, Object> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, Object> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return errors;
		}
		userDTO.setId(id);
		UserDTO updatedUser = userService.updateUser(userDTO);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "User updated successfully");
		response.put("userId", updatedUser.getId().toString());
		return response;
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			userService.deleteById(id);
			return new ResponseEntity<>("Succesfully deleted",HttpStatus.OK);
		
	}

}
