package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.model.Admin;

public interface AdminRepository  extends JpaRepository<Admin, Integer> {

}
