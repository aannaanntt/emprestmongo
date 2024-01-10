package com.mohit.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mohit.spring.boot.entity.Employee;
import com.mohit.spring.boot.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee emp) {
        return repo.save(emp);

    }

    public String deleteEmployee(String employeeUsername) {
        Optional<Employee> employee = repo.findByUsername(employeeUsername);
       return employee.map(e -> { repo.delete(e); return "Employee deleted successfully."; }).orElse("Employee not found.");
    }

    public String updateEmployee(String employeeUsername, Employee updatedEmployee) {
        Optional<Employee> employee = repo.findByUsername(employeeUsername);
        if (employee.isPresent()) {
            deleteEmployee(employeeUsername);
            repo.save(updatedEmployee);
            return "Updated Employee by the name of " + employeeUsername;
        }
       return "Unable to find employee by the name of " + employeeUsername;
    }

    public Optional<Employee> findByUsername(String username) { return repo.findByUsername(username); }
    public Optional<Employee> findByID(int id) { return repo.findById(id); }

}

