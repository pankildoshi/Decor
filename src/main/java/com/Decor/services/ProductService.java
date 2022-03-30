package com.Decor.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.Product;
import com.Decor.repos.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
	
	public void addProduct(Product product) {
		repo.save(product);
	}
	
	public List<Product> getProducts(){
		return repo.findAll();
	}
	
	public Product getProduct(int id) {
		return repo.getById(id);
	}
	
	public void deleteProduct(int id) {
		repo.deleteById(id);
	}
	
	public int totalProducts() {
		return (int) repo.count();
	}

	public void updateQty(Product product) {
		repo.setQuantityById(product.getQty(), product.getId());	
	}
}
