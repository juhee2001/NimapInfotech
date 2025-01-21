package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductDTO;
import com.app.model.Product;
import com.app.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService ps;
	

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) 
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pp=ps.getAllProducts(pageable);
        return new ResponseEntity<Page<Product>>(pp, HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product prd)
    {
    	Product p=ps.createProduct(prd);
    	return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id) {
        Product product = ps.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDTO pDTO = new ProductDTO(product);
        return new ResponseEntity<>(pDTO, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,@RequestBody Product prd)
    {
    	Product p=ps.updateProduct(id,prd);
    	return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id)
    {
    	ps.deleteProduct(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	
    }
}
