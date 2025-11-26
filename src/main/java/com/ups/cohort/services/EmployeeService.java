package com.ups.cohort.services;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.EmployeeDto;
import com.ups.cohort.entities.EmployeeEntity;
import com.ups.cohort.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
		employeeEntity = employeeRepository.save(employeeEntity);
		return modelMapper.map(employeeEntity, EmployeeDto.class);
	}

	public EmployeeDto getEmployeeById(Long employeeId) {
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
		return modelMapper.map(employeeEntity, EmployeeDto.class);
	}

	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
		return employeeEntities
				.stream()
				.map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
				.toList();
	}

	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);


		employeeEntity.setEmail(employeeDto.getEmail());
		employeeEntity.setDepartment(employeeDto.getDepartment());
		employeeEntity.setName(employeeDto.getName());
		employeeEntity.setSalary(employeeDto.getSalary());

		return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDto.class);

	}

	public EmployeeDto updateEmployeePartially(Long employeeId, Map<String, Object> updates) {
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);

		updates.forEach((key , value) -> {
			switch (key) {
				case "name":
					employeeEntity.setName((String) value);
					break;
				case "email":
					employeeEntity.setEmail((String) value);
					break;

				case "department":
					employeeEntity.setDepartment((String) value);
					break;
				case "salary":
					employeeEntity.setSalary((double) value);
					break;

			}
		});

		return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDto.class);

	}

	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
