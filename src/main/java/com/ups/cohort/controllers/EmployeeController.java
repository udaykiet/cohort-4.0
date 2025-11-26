package com.ups.cohort.controllers;

import java.util.List;
import java.util.Map;

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

	@PostMapping()
	public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		return employeeService.createEmployee(employeeDto);
	}

	@GetMapping("/{employeeId}")
	public EmployeeDto getEmployee(@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@GetMapping()
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.getAllEmployees();
	}

	@PutMapping("/{employeeId}")
	public EmployeeDto updateEmployee(@PathVariable Long employeeId,
	                                  @RequestBody EmployeeDto employeeDto){
		return employeeService.updateEmployee(employeeId , employeeDto);
	}

	@PatchMapping("/{employeeId}")
	public EmployeeDto updateEmployeePartially(@PathVariable Long employeeId,
	                                           @RequestBody Map<String , Object> updates){
		return employeeService.updateEmployeePartially(employeeId , updates);
	}



	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(@PathVariable Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return "employee Deleted with id " + employeeId;
	}






















	@GetMapping("/health")
	public String healthCheck() {
		return "This is the health check";
	}
}
