package com.expense.model;

import java.time.LocalDateTime;
import java.util.List;

import com.expense.model.constant.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "FirstName")
	private String FirstName;

	@Column(name = "LastName")
	private String LastName;

	@Column(name = "Email")
	private String email;
	@Column(name = "Password")
	private String password;
	
//	@Column(name="UserType")
//	private UserType usertype;
//	@Column(name="account_status")
//	private String accountStatus; // "ACTIVE", "LOCKED"
//	@Column(name="login_attempts")
//	private Integer loginAttempts;
//	@Column(name="locked_date_time")
//	private LocalDateTime lockedDateTime;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Wallet wallet;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

}
