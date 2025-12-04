package com.ups.cohort.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.BrandDto;
import com.ups.cohort.entities.BrandEntity;
import com.ups.cohort.repositories.BrandRepository;

@Service
public class BrandService {

	private final BrandRepository brandRepository;
	private final ModelMapper modelMapper;

	public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
		this.brandRepository = brandRepository;
		this.modelMapper = modelMapper;
	}


	//CREATE NEW BRAND
	public BrandDto createBrand(BrandDto brandDto) {
		BrandEntity brandEntity = modelMapper.map(brandDto , BrandEntity.class);
		return modelMapper.map(brandRepository.save(brandEntity) , BrandDto.class);
	}


}
