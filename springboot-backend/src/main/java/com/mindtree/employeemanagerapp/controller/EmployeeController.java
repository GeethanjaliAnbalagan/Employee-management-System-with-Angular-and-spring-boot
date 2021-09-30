package com.mindtree.employeemanagerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// get all employees
	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees(){
		return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.ACCEPTED);
	}		
	
	// create employee rest api
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity(employeeService.createEmployee(employee), HttpStatus.CREATED);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.ACCEPTED);
	}
	
	// update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployeeDetails){
		return new ResponseEntity(employeeService.updateEmployeeByIdWithNewEmployee(id, newEmployeeDetails), HttpStatus.ACCEPTED);
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
		return new ResponseEntity(employeeService.deleteEmployee(id), HttpStatus.GONE);
	}
	
	
}
