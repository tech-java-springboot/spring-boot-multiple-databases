package com.codeonce.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeonce.model.customer.Customer;
import com.codeonce.model.product.Product;
import com.codeonce.repo.customer.CustomerRepository;
import com.codeonce.repo.product.ProductRepository;

@RestController
@RequestMapping("/api/multiDB")
public class MultiRestController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/create/products")
	public ResponseEntity<String> createProducts(@RequestBody List<Product> products){
		ResponseEntity<String> response = null;
		List<Product> list = productRepository.saveAll(products);
		if(list.size()>0)
			response = new ResponseEntity<String>("Products are created!", HttpStatus.CREATED);
		else
			response = new ResponseEntity<String>("Failed to create products, something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	@PostMapping("/create/customers")
	public ResponseEntity<String> createCustomers(@RequestBody List<Customer> customers){
		ResponseEntity<String> response = null;
		List<Customer> list = customerRepository.saveAll(customers);
		if(list.size()>0)
			response = new ResponseEntity<String>("Customers are created!", HttpStatus.CREATED);
		else
			response = new ResponseEntity<String>("Failed to create customers, something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	@GetMapping("/get/products")
	public ResponseEntity<List<Product>> getProducts(){
		ResponseEntity<List<Product>> response = null;
		List<Product> list = productRepository.findAll();
		if(list.size()>0)
			response = new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		else
			response = new ResponseEntity<List<Product>>(Arrays.asList(null), HttpStatus.NOT_FOUND);
		return response;
	}
	
	@GetMapping("/get/customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		ResponseEntity<List<Customer>> response = null;
		List<Customer> list = customerRepository.findAll();
		if(list.size()>0)
			response = new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		else 
			response = new ResponseEntity<List<Customer>>(Arrays.asList(null), HttpStatus.NOT_FOUND);
		return response;
	}
}
