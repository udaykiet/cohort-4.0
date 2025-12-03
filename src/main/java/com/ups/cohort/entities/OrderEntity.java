package com.ups.cohort.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(
		name = "orders",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_order_number", columnNames = "orderNumber")
		},
		indexes = {
				@Index(name = "idx_order_status", columnList = "orderStatus"),
				@Index(name = "idx_payment_status", columnList = "paymentStatus")
		}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String orderNumber;

	@Column(nullable = false)
	private BigDecimal totalAmount;

	@Column(nullable = false, length = 30)
	private String orderStatus;  // PLACED, SHIPPED, DELIVERED, CANCELLED

	@Column(nullable = false, length = 30)
	private String paymentStatus; // PAID, UNPAID, REFUNDED

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;
}
