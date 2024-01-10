package com.mohit.spring.boot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.spring.boot.entity.Employee;
import com.mohit.spring.boot.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping("/home")
	 public String homePage() {
		 return "Welcome to employee management system";
	 }
	
	  @GetMapping
	    public ResponseEntity<List<Employee>> getAllEmployees() { return new ResponseEntity<>(employeeservice.findAll(), HttpStatus.OK); }
	  

    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
    Optional<Employee> employee = employeeservice.findByUsername(username);
    return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}
    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable int id) {
        Optional<Employee> employee = employeeservice.findByID(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public Employee createEmployee(@RequestBody Employee employee) { return employeeservice.addEmployee(employee); }

    @DeleteMapping("/remove/{username}")
    public String removeEmployee(@PathVariable String username) { return  employeeservice.deleteEmployee(username); }

    @PutMapping("/update/{username}")
    public String updateEmployee(@PathVariable String username, @RequestBody Employee updatedEmployee) {
        return employeeservice.updateEmployee(username, updatedEmployee);
    }
}
