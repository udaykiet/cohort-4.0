package com.ups.cohort.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.EmployeeDto;
import com.ups.cohort.entities.EmployeeEntity;
import com.ups.cohort.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public EmployeeService(EmployeeRepository employeeRepository , ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
		employeeEntity = employeeRepository.save(employeeEntity);
		return modelMapper.map(employeeEntity, EmployeeDto.class);
	}
}
