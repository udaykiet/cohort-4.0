package com.ups.cohort.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.CategoryDto;
import com.ups.cohort.entities.CategoryEntity;
import com.ups.cohort.exceptions.ResourceNotFoundException;
import com.ups.cohort.repositories.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository , ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	// CREATE
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryEntity category = modelMapper.map(categoryDto, CategoryEntity.class);
		CategoryEntity savedCategory = categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	// READ - all categories
	public Page<CategoryDto> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable)
				.map(category -> modelMapper.map(category, CategoryDto.class));
	}

	// READ - by id
	public CategoryDto getCategoryById(Long id) {
		CategoryEntity category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + id));
		return modelMapper.map(category, CategoryDto.class);
	}

	// UPDATE
	public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
		CategoryEntity existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + id));

		modelMapper.map(categoryDto, existingCategory);

		CategoryEntity updatedCategory = categoryRepository.save(existingCategory);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	// DELETE
	public void deleteCategory(Long id) {
		CategoryEntity existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + id));

		categoryRepository.delete(existingCategory);
	}
}
