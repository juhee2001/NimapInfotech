package com.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.model.Product;

public interface ProductService {

	public Page<Product> getAllProducts(Pageable pageable);

	public Product createProduct(Product prd);

	public Product getProductById(int id);

	public Product updateProduct(int id, Product prd);

	public void deleteProduct(int id);

}
