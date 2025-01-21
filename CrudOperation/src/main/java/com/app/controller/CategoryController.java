package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.model.Category;
import com.app.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	CategoryService cs;
	
	
	    @GetMapping
	    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page, 
	            @RequestParam(defaultValue = "10") int size) 
	    {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Category> pc= cs.getAllCategories(pageable);
	        return new ResponseEntity<Page<Category>>(pc, HttpStatus.OK);
	    }
	    
	    @PostMapping()
	    public ResponseEntity<Category> createCategory(@RequestBody Category cat)
	    {
	    	Category c=cs.createCategory(cat);
	    	return new ResponseEntity<Category>(c,HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id)
	    {
	    	Category c=cs.getCategoryById(id);
	    	return new ResponseEntity<Category>(c,HttpStatus.OK);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id,@RequestBody Category cat)
	    {
	    	Category c=cs.updateCategory(id,cat);
	    	return new ResponseEntity<Category>(c,HttpStatus.ACCEPTED);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id)
	    {
	    	cs.deleteCategory(id);
	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    
}
