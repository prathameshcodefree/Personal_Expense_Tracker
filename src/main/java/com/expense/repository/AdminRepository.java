package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer> {

}
