package com.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.domain.Product;

public class ProductResource extends ResourceSupport {

	private final long id;
	private final String description;
	private final int buy_price;
	private final int sell_price;
	private final int quantity;
	
	public ProductResource(Product product) {
		id = product.getId();
		description = product.getDescription();
		buy_price = product.getBuy_price();
		sell_price = product.getSell_price();
		quantity = product.getQuantity();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public int getSell_price() {
		return sell_price;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
