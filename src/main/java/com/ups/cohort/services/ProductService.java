package com.ups.cohort.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ups.cohort.dtos.ProductDto;
import com.ups.cohort.dtos.UpdateProductBrandRequest;
import com.ups.cohort.dtos.UpdateProductCategoryRequest;
import com.ups.cohort.dtos.response.ProductListDto;
import com.ups.cohort.entities.BrandEntity;
import com.ups.cohort.entities.CategoryEntity;
import com.ups.cohort.entities.ProductEntity;
import com.ups.cohort.exceptions.ResourceNotFoundException;
import com.ups.cohort.repositories.BrandRepository;
import com.ups.cohort.repositories.CategoryRepository;
import com.ups.cohort.repositories.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	private final BrandRepository brandRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, BrandRepository brandRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
		this.brandRepository = brandRepository;
	}


	// CREATE
	public ProductDto createProduct(ProductDto requestDto) {
		ProductEntity product = modelMapper.map(requestDto, ProductEntity.class);

//		 IMPORTANT STEP
		if (requestDto.getCategory() != null && requestDto.getCategory().getId() != null) {
			CategoryEntity category = categoryRepository.findById(requestDto.getCategory().getId())
					.orElseThrow(() -> new RuntimeException("Category not found"));
			product.setCategory(category); // overwrite lazy proxy
		}


		if (requestDto.getBrand() != null && requestDto.getBrand().getId() != null) {
			BrandEntity brand = brandRepository.findById(requestDto.getBrand().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Brand Not Found with id:" + requestDto.getBrand().getId()));
			product.setBrand(brand);
		}


		if (product.getProductDetail() != null) {
			product.getProductDetail().setProduct(product);
		}

		ProductEntity savedProduct = productRepository.save(product);
//		System.out.println(savedProduct.getCategory().getName());
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
				.map(productEntity -> modelMapper.map(productEntity, ProductDto.class))
				.collect(Collectors.toList());
	}

	//UPDATE CATEGORY OF THE PRODUCT
	public ProductDto updateProductCategory(Long productId, UpdateProductCategoryRequest updateProductCategoryRequest) {
		ProductEntity existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));

		CategoryEntity existingCategory = categoryRepository.findById(updateProductCategoryRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + updateProductCategoryRequest.getCategoryId()));

		existingProduct.setCategory(existingCategory);

		return modelMapper.map(productRepository.save(existingProduct), ProductDto.class);

	}

	public List<ProductListDto> fetchAllProductOfACategory(Long categoryId) {
		CategoryEntity category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

		List<ProductEntity> productEntities = productRepository.findByCategoryId(categoryId);
		return productEntities
				.stream()
				.map(productEntity -> modelMapper.map(productEntity, ProductListDto.class))
				.collect(Collectors.toList());
	}


	public ProductDto updateProductBrand(Long productId , UpdateProductBrandRequest updateProductBrandRequest) {
		ProductEntity product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));

		BrandEntity brand = brandRepository.findById(updateProductBrandRequest.getId())
				.orElseThrow(() -> new ResourceNotFoundException("brand not found with id: " + updateProductBrandRequest.getId()));

		product.setBrand(brand);
		return modelMapper.map(productRepository.save(product) , ProductDto.class);
	}
}

