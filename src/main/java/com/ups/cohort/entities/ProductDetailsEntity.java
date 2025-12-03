package com.ups.cohort.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String color;

	@Column(length = 100)
	private String dimensions;

	private Double weight;

	@Column(length = 120)
	private String material;

	private Integer warrantyMonths;

	@Column(length = 150)
	private String manufacturer;

	@Column(length = 150)
	private String modelNumber;


//
//	@OneToOne(
//			mappedBy = "productDetail"
//	)
//	private ProductEntity product; // INVERSE SIDE
}