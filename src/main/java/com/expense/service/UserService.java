package com.expense.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.model.DTO.LoginRequestDTO;
import com.expense.model.DTO.LoginResponseDTO;
import com.expense.model.DTO.UserDTO;
import com.expense.repository.UserRepository;
import com.expense.util.PasswordEncoderUtil;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	PasswordEncoderUtil passwordEncoderUtil;
	
	@Autowired
	ModelMapper modelMapper;

	public List<UserDTO> getAllUser() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}

	public UserDTO getUserById(Long id) {
		Optional<User> uSerO = userRepository.findById(id);
		return uSerO.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);

	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		System.out.println(user.getFirstName());
		user.setPassword(passwordEncoderUtil.encodePassword(userDTO.getPassword()));
		user = userRepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}

	public UserDTO updateUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoderUtil.encodePassword(userDTO.getPassword()));
		user = userRepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}

	public String deleteById(Long id) {
		userRepository.deleteById(id);
		return "Succcesfully deleted";
	}

	//////////////////////////////////////////////////////////

	public User login(LoginRequestDTO loginRequestDto) {
		Optional<User> UserO = userRepository.findUserByUserName(loginRequestDto.getUserName());

		User user = null;

		if (UserO.isPresent()) {

			User userdb = UserO.get();

//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if (passwordEncoderUtil.matches(loginRequestDto.getPassword(), userdb.getPassword())) {
				user = userdb;
			}

		}

		return user;
	}

	public User findUserByUsername(String userName) {
		return userRepository.findUserByUserName(userName).orElse(null);
	}

//	public boolean isUserValid(User user) {
//		return user != null && !"LOCKED".equals(user.getAccountStatus());
//	}

	
	/*
	 * public void lockAccount(User user) { user.setAccountStatus("LOCKED");
	 * user.setLockedDateTime(LocalDateTime.now()); saveStudent(user); }
	 */
	/*
	 * public void resetLoginAttempts(User user) { user.setLoginAttempts(0);
	 * saveStudent(student); }
	 */

	/*
	 * public LoginResponseDTO createInvalidStudentResponse(LoginResponseDTO
	 * loginResponseDto, Student student) { loginResponseDto.setStatus(false);
	 * 
	 * String message = "Account is locked. Please try again after 24 hours."; if
	 * ("LOCKED".equals(student.getAccountStatus()) && isAccountLocked(student)) {
	 * student.setAccountStatus("ACTIVE"); student.setLoginAttempts(0);
	 * saveStudent(student); message = "Account unlocked. Please try again."; }
	 * 
	 * loginResponseDto.setMessage(message); return loginResponseDto; }
	 * 
	 * public boolean isAccountLocked(Student student) { LocalDateTime lockDateTime
	 * = student.getLockedDateTime(); LocalDateTime currentDateTime =
	 * LocalDateTime.now(); return
	 * lockDateTime.plusHours(24).isBefore(currentDateTime); }
	 */
	/*
	 * public Student findStudentByUsername(String username) { return
	 * studentRepository.findByUserName(username).orElse(null); }
	 * 
	 * public boolean verifyPassword(String rawPassword, String encodedPassword) {
	 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); return
	 * passwordEncoder.matches(rawPassword, encodedPassword); }
	 */

}
