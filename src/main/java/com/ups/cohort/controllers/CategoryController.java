package com.ups.cohort.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ups.cohort.dtos.CategoryDto;
import com.ups.cohort.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory = categoryService.createCategory(categoryDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdCategory);
	}

	// READ - paginated, sorted
	@GetMapping
	public ResponseEntity<Page<CategoryDto>> getAllCategories(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection
	) {

		Sort sort = sortDirection.equalsIgnoreCase("asc")
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<CategoryDto> categories = categoryService.getAllCategories(pageable);

		return ResponseEntity.ok(categories);
	}

	// READ - by ID
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
		CategoryDto category = categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(
			@PathVariable Long id,
			@RequestBody CategoryDto categoryDto
	) {
		CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
		return ResponseEntity.ok(updatedCategory);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body("Category deleted successfully");
	}
}