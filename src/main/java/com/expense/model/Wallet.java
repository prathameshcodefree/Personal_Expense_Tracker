package com.expense.model;

import com.expense.model.constant.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Wallet   extends AuditColumn {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private Long id;

    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    


}
