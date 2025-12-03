package com.ups.cohort.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId;     // will be replaced with @ManyToOne later
	private Long productId;   // will be replaced with @ManyToOne later

	@Column(nullable = false, length = 150)
	private String productName; // store product snapshot

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private BigDecimal price; // price at purchase time

	@Column(nullable = false)
	private BigDecimal subTotal;
}
