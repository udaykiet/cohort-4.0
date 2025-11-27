package com.ups.cohort.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ups.cohort.dtos.EmployeeDto;
import com.ups.cohort.services.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	//CREATE
	@PostMapping()
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto created = employeeService.createEmployee(employeeDto);

		return ResponseEntity
				.status(HttpStatus.CREATED) //201
				.body(created);
	}

	//GET BY ID
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long employeeId) {
		EmployeeDto employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);

	}

	//GET ALL
	@GetMapping()
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	//UPDATE FULL
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId,
	                                                  @RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = employeeService.updateEmployee(employeeId, employeeDto);
		return ResponseEntity.ok(employee);
	}

	//UPDATE PARTIAL
	@PatchMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployeePartially(@PathVariable Long employeeId,
	                                                           @RequestBody Map<String, Object> updates) {
		EmployeeDto employee = employeeService.updateEmployeePartially(employeeId, updates);
		return ResponseEntity.ok(employee);
	}


	//DELETE
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT) // 204
				.body("Employee deleted with id : " + employeeId);
	}


	@GetMapping("/health")
	public String healthCheck() {
		return "This is the health check";
	}
}
