package com.ups.cohort.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
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

import com.ups.cohort.dtos.ProductDto;
import com.ups.cohort.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		ProductDto createdProduct = productService.createProduct(productDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(createdProduct);
	}

	// READ - all products
	@GetMapping
	public ResponseEntity<Page<ProductDto>> getAllProducts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection
			) {

		Sort sort = sortDirection.equalsIgnoreCase("asc")
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, pageSize , sort);
		Page<ProductDto> products = productService.getAllProducts(pageable);
		return ResponseEntity.ok(products);
	}

	// READ - by id
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
		ProductDto product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
	                                                @RequestBody ProductDto productDto) {
		ProductDto updatedProduct = productService.updateProduct(id, productDto);
		return ResponseEntity.ok(updatedProduct);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body("product deleted successfully");
	}
}
