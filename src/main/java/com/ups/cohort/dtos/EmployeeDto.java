package com.ups.cohort.dtos;

import com.ups.cohort.validators.StartsWithCapital;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, message = "Name should have at least 2 characters")
	@StartsWithCapital
	private String name;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Department is required")
	private String department;

	@Positive(message = "Salary must be greater than 0")
	private Double salary;
}

