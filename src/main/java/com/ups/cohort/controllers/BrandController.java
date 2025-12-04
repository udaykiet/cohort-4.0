package com.ups.cohort.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ups.cohort.dtos.BrandDto;
import com.ups.cohort.dtos.response.ProductListDto;
import com.ups.cohort.services.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}


	@PostMapping
	public ResponseEntity<BrandDto> createBrand(
			@RequestBody BrandDto brandDto
	){
		BrandDto brand = brandService.createBrand(brandDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(brand);
	}


	@GetMapping("/{brandId}/products")
	public ResponseEntity<List<ProductListDto>> fetchProductsOfBrand(
			@PathVariable Long brandId
	){
		return ResponseEntity.ok(brandService.fetchProductsOfBrand(brandId));
	}

}
