package com.rest.repository;

import org.springframework.stereotype.Repository;

import com.rest.domain.Product;

@Repository
public class ProductRepository extends InMemoryRepository<Product> {

	protected void updateIfExists(Product original, Product updated) {
		original.setDescription(updated.getDescription());
		original.setBuy_price(updated.getBuy_price());
		original.setSell_price(updated.getSell_price());
		original.setQuantity(updated.getQuantity());
	}
}
