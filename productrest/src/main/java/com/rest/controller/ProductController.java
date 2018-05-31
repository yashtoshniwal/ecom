package com.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.domain.Product;
import com.rest.repository.ProductRepository;
import com.rest.resource.ProductResource;
import com.rest.resource.ProductResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Product.class)
@RequestMapping(value = "/api/products", produces = "application/json")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ProductResource>> findAllOrders() {
		List<Product> products = repository.findAll();
		return new ResponseEntity<>(assembler.toResourceCollection(products), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ProductResource> createOrder(@RequestBody Product product) {
		Product createdProduct = repository.create(product);
		return new ResponseEntity<>(assembler.toResource(createdProduct), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductResource> findProductById(@PathVariable Long id) {
		Optional<Product> product = repository.findById(id);

		if (product.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(product.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ProductResource> updateOrder(@PathVariable Long id, @RequestBody Product updatedProduct) {
		boolean wasUpdated = repository.update(id, updatedProduct);
		
		if (wasUpdated) {
			return findProductById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
