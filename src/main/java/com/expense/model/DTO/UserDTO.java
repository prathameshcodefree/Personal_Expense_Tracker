package com.expense.model.DTO;

import com.expense.model.AuditColumn;
import com.expense.model.constant.UserType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO  extends AuditColumn 	{
	
	
	private Long id;
	@NotBlank(message = "UserName Should not be Blank")
	private String userName;
	@NotBlank(message = "FirstName Should not be Blank")
	private String firstName;
	@NotBlank(message = "LastName Should not be Blank")
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, max = 15, message = "Password must be at least 6 characters long")
	private String password;

	@Enumerated(EnumType.STRING)
	private UserType usertype;



	
	


	
	

}
