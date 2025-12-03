package com.ups.cohort.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.ProductDto;
import com.ups.cohort.entities.ProductEntity;
import com.ups.cohort.exceptions.ResourceNotFoundException;
import com.ups.cohort.repositories.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	public ProductService(ProductRepository productRepository , ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}


	// CREATE
	public ProductDto createProduct(ProductDto requestDto) {
		ProductEntity product = modelMapper.map(requestDto, ProductEntity.class);

		if(product.getProductDetail() != null){
			product.getProductDetail().setProduct(product);
		}


		ProductEntity savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductDto.class);
	}


	// READ - all products
	public Page<ProductDto> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable)
				.map(product -> modelMapper.map(product, ProductDto.class));
	}

	// READ - by id
	public ProductDto getProductById(Long id) {
		ProductEntity product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + id));
		return modelMapper.map(product, ProductDto.class);
	}

	// UPDATE
	public ProductDto updateProduct(Long id, ProductDto requestDto) {
		ProductEntity existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + id));

		// Map fields from DTO to existing entity
		modelMapper.map(requestDto, existingProduct);

		ProductEntity updatedProduct = productRepository.save(existingProduct);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}

	// DELETE
	public void deleteProduct(Long id) {
		ProductEntity existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + id));
		productRepository.delete(existingProduct);
	}

	public List<ProductDto> filterOnStock(int stock) {
		List<ProductEntity> products = productRepository.findByStockLessThan(stock);
		return products
				.stream()
				.map(productEntity -> modelMapper.map(productEntity , ProductDto.class))
				.collect(Collectors.toList());
	}
}
