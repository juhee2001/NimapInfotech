package com.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository pr;

	@Override
	public Page<Product> getAllProducts(Pageable pageable) 
	{
		return pr.findAll(pageable);
	}

	@Override
	public Product createProduct(Product prd) 
	{
		return pr.save(prd);
	}

	@Override
	public Product getProductById(int id) 
	{
		Optional<Product> op=pr.findById(id);
		if(op.isPresent())
		{
			Product c=op.get();
			Product updated=pr.save(c);
			return updated;
		}
		
		return null;
	}

	@Override
	public Product updateProduct(int id,Product prd) 
	{
		Optional<Product> op=pr.findById(id);
		if(op.isPresent())
		{
			Product p=op.get();
			p.setName(prd.getName());
			p.setPrice(prd.getPrice());
			p.setCategory(prd.getCategory());
			return pr.save(p);
		}
		throw new ResourceNotFoundException("Product with id" +id+ "not found");
		
	}

	@Override
	public void deleteProduct(int id) {
		pr.deleteById(id);
		
	}
}
