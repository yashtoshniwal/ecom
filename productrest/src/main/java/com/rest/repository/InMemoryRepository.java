package com.rest.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.domain.Identifiable;


public abstract class InMemoryRepository<T extends Identifiable> {

	@Autowired
	private IdGenerator idGenerator;
	
	private List<T> products = Collections.synchronizedList(new ArrayList<>());

	public T create(T product) {
		products.add(product);
		product.setId(idGenerator.getNextId());
		return product;
	}

	public boolean delete(Long id) {
		return products.removeIf(product -> product.getId().equals(id));
	}

	public List<T> findAll() {
		return products;
	}

	public Optional<T> findById(Long id) {
		return products.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	public int getCount() {
		return products.size();
	}

	public void clear() {
		products.clear();
	}

	public boolean update(Long id, T updated) {
		
		if (updated == null) {
			return false;
		}
		else {
			Optional<T> product = findById(id);
			product.ifPresent(original -> updateIfExists(original, updated));
			return product.isPresent();
		}
	}
	
	protected abstract void updateIfExists(T original, T desired);

}
