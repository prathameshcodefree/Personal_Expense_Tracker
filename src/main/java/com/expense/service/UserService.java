package com.expense.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.model.DTO.UserDTO;
import com.expense.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;
	
	
	
	public List<UserDTO> getAllUser() {
		List<User> users=userRepository.findAll();
		return users.stream().map(user->modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}
	
	public  UserDTO getUserById(Long id) {
		Optional<User> uSerO=userRepository.findById(id);
		return uSerO.map(user -> modelMapper.map(user,UserDTO.class)).orElse(null);
		
	}
	
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
    
    public void deleteById(Long id) {
    	userRepository.deleteById(id);
    }

}
