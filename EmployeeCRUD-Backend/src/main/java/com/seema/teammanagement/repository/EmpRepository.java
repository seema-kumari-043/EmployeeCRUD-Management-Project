package com.seema.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seema.teammanagement.model.Employee;

@Repository 
public interface EmpRepository extends JpaRepository<Employee, Long> {
	
}