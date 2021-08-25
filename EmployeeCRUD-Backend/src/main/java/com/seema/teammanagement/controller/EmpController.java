package com.seema.teammanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seema.teammanagement.exception.ResourceNotFoundException;
import com.seema.teammanagement.model.Employee;
import com.seema.teammanagement.repository.EmpRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpController {
	
	@Autowired 
	private EmpRepository emprepo;
	
	//get all employees
	
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return emprepo.findAll();
    }
  
	//Create employee rest api

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return emprepo.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = emprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee don't exist with id : "+id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = emprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee don't exist with id : "+id));
		
		employee.setFname(employeeDetails.getFname());
		employee.setLname(employeeDetails.getLname());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updateEmployee = emprepo.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
   
	//delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = emprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee don't exist with id : "+id));
	          
		emprepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

}
