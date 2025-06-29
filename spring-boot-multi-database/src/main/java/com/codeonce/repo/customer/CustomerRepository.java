package com.codeonce.repo.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeonce.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
