package com.hibernate.sringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.sringboot.model.Employee;

@Repository
public interface EmployeReppository extends JpaRepository<Employee, Long>{

	
}
