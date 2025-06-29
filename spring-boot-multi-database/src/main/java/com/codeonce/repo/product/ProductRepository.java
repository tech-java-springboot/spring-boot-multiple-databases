package com.codeonce.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeonce.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
