package com.codeonce.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m-product-tab")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	@Id
	private Integer id;
	private String code;
	private String name;
}
