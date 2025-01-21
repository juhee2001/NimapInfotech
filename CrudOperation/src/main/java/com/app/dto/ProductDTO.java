package com.app.dto;

import com.app.model.Product;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String categoryName;

  
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        if (product.getCategory() != null) 
        {
            this.categoryName = product.getCategory().getName();
        } 
        else 
        {
            this.categoryName = null; 
        }
        
    }

   
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
