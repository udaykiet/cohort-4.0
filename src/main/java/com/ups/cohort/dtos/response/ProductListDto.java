package com.ups.cohort.dtos.response;

import java.math.BigDecimal;

import com.ups.cohort.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
}
