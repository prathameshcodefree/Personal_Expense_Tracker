package com.expense.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
	
	private String userName;
	private String email;
	
	private String password;
	
	private Integer id;
	



	
}
