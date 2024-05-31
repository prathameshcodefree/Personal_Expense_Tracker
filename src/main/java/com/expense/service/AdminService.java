package com.expense.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.model.DTO.UserDTO;
import com.expense.repository.AdminRepository;
import com.expense.util.PasswordEncoderUtil;

@Service
public class AdminService {
	PasswordEncoderUtil passwordEncoderUtil;
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AdminRepository adminrepository;
		
	
	public UserDTO createAdmin(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
//		System.out.println(user.getFirstName());
		user.setPassword(passwordEncoderUtil.encodePassword(userDTO.getPassword()));
		 adminrepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}
	


}
