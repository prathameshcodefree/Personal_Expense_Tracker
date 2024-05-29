package com.expense.model;

import java.io.Serializable;
import java.util.Date;

import com.expense.model.constant.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditColumn  implements Serializable {

	@Enumerated(EnumType.STRING)
	@Column(name = "updated_by_type")
	private UserType updatedByType;


	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "created_by_type")
	private UserType createdByType;
	
	@Column(name = "created_at")
	private Date createdAt;


}