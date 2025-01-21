package com.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.CategoryRepository;
import com.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository cr;

	@Override
	public Page<Category> getAllCategories(Pageable pageable) 
	{
		
		return cr.findAll(pageable);
	}

	@Override
	public Category createCategory(Category category) {
	  
	    if (category.getProducts() != null) {
	        for (Product product : category.getProducts()) {
	            product.setCategory(category);
	        }
	    }
	    return cr.save(category);
	}

	@Override
	public Category getCategoryById(int id)
	{
		Optional<Category> op=cr.findById(id);
		if(op.isPresent())
		{
			Category c=op.get();
			return c;
		}
		throw new ResourceNotFoundException("Category with id" +id+ "not found");
	}

	@Override
	public Category updateCategory(int id,Category cat) 
	{
		Optional<Category> op=cr.findById(id);
		if(op.isPresent())
		{
			Category c=op.get();
			c.setName(cat.getName());
			return cr.save(c);
		}
		
		return null;
	}

	@Override
	public void deleteCategory(int id) {
		cr.deleteById(id);
	}

	
}
