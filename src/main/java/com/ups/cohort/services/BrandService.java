package com.ups.cohort.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.BrandDto;
import com.ups.cohort.dtos.ProductDto;
import com.ups.cohort.dtos.response.ProductListDto;
import com.ups.cohort.entities.BrandEntity;
import com.ups.cohort.exceptions.ResourceNotFoundException;
import com.ups.cohort.repositories.BrandRepository;
import com.ups.cohort.repositories.ProductRepository;

@Service
public class BrandService {

	private final BrandRepository brandRepository;
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	public BrandService(BrandRepository brandRepository, ModelMapper modelMapper , ProductRepository productRepository) {
		this.brandRepository = brandRepository;
		this.modelMapper = modelMapper;
		this.productRepository = productRepository;
	}


	//CREATE NEW BRAND
	public BrandDto createBrand(BrandDto brandDto) {
		BrandEntity brandEntity = modelMapper.map(brandDto , BrandEntity.class);
		return modelMapper.map(brandRepository.save(brandEntity) , BrandDto.class);
	}


	public List<ProductListDto> fetchProductsOfBrand(Long brandId) {

		BrandEntity brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found with id : " + brandId));

		return productRepository.findByBrandId(brandId)
				.stream()
				.map(productEntity -> modelMapper.map(productEntity , ProductListDto.class))
				.collect(Collectors.toList());
	}
}
