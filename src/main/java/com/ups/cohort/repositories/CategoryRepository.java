package com.ups.cohort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ups.cohort.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> {
}
