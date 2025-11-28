package com.ups.cohort;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ups.cohort.entities.ProductEntity;
import com.ups.cohort.repositories.ProductRepository;

@SpringBootTest
public class ControllerTest {

	@Autowired
	private  ProductRepository productRepository;

	@Test
	void findByName(){
		Optional<ProductEntity> product =  productRepository.findByName("Apple iPhone 15");
		product.ifPresent(p -> System.out.println(p.getName()));
	}


}
