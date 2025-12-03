package com.ups.cohort.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ups.cohort.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , Long> {

	Optional<ProductEntity> findByName(String name);

	List<ProductEntity> findByStockLessThan(int stock);

}

