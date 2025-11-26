package com.ups.cohort.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ups.cohort.dtos.EmployeeDto;
import com.ups.cohort.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping()
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.createEmployee(employeeDto);
	}

	@GetMapping("/health")
	public String healthCheck() {
		return "This is the health check";
	}
}
