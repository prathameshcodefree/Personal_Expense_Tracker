package com.expense.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.model.User;
import com.expense.model.DTO.LoginRequestDTO;
import com.expense.model.DTO.LoginResponseDTO;
import com.expense.model.DTO.UserDTO;
import com.expense.security.JWTService;
import com.expense.service.AdminService;
import com.expense.service.TokenLogService;
import com.expense.service.UserService;
import com.expense.util.PasswordEncoderUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;

	@Autowired
	JWTService jwtService;

	@Autowired
	TokenLogService tokenLogService;

	PasswordEncoderUtil passwordEncoderUtil;

	@PostMapping("/user/login")
	public LoginResponseDTO studentLogin(@RequestBody LoginRequestDTO loginRequestDto) {

		LoginResponseDTO loginResponseDto = new LoginResponseDTO();

		if (loginRequestDto.getUserName() == null || loginRequestDto.getUserName().isEmpty()
				|| loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isEmpty()) {
			loginResponseDto.setStatus(false);
			loginResponseDto.setMessage("Username or password cannot be empty");
			return loginResponseDto;
		}

		User user = userService.findUserByUsername(loginRequestDto.getUserName());

		/*
		 * // Check if password matches boolean passwordMatches =
		 * userService.verifyPassword(loginRequestDto.getPassword(),
		 * user.getPassword()); if (!passwordMatches) { // Increment login attempts
		 * userService.handleIncorrectPassword(loginResponseDto, user); return
		 * loginResponseDto; }
		 */

		// Check if account is locked
		/*
		 * if (user.getAccountStatus().equals("locked")) { LocalDateTime currentTime =
		 * LocalDateTime.now(); LocalDateTime unlockTime =
		 * user.getLockedDateTime().plusHours(24); if (currentTime.isBefore(unlockTime))
		 * { throw new RuntimeException("Account locked. Please try again later."); }
		 * else { user.setAccountStatus("active"); user.setLoginAttempts(0);
		 * user.setLockedDateTime(null); } }
		 */

		// Check password
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoderUtil.matches(loginRequestDto.getPassword(), user.getPassword())) {
			// Reset login attempts and update last login

			// Generate token
			String token = jwtService.generateToken(user);

			// Response preparation
			UserDTO userDto = new UserDTO();
			userDto.setFirstName(user.getFirstName());
			userDto.setUserName(user.getUserName());
			userDto.setId(user.getId());

			loginResponseDto.setStatus(true);
			loginResponseDto.setMessage("User Login Successfully");
			loginResponseDto.setUser(userDto);
			loginResponseDto.setToken(token);

		}

		// Response send
		return loginResponseDto;

	}

	@PostMapping("/user/signup")
    public Map<String, String> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return errors;
        }

        UserDTO createdUser;
        if ("Admin".equals(userDTO.getUsertype())) {
            createdUser = adminService.createAdmin(userDTO);
        } else {
            createdUser = userService.createUser(userDTO);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("userId", createdUser.getId().toString());
        return response;
    }

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam String token) {
		if (tokenLogService.invalidateToken(token)) {
			return ResponseEntity.ok("Logout successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token not found");
		}
	}

}
