package com.ups.cohort.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
	private Long id;
	private String color;
	private String dimensions;
	private Double weight;
	private String material;
	private Integer warrantyMonths;
	private String manufacturer;
	private String modelNumber;
}