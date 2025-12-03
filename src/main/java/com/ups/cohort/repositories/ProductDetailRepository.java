package com.ups.cohort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ups.cohort.entities.ProductDetailsEntity;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailsEntity , Long> {
}
