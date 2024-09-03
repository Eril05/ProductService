package com.ecommerce.productservice.model;

import lombok.Data;

@Data
public class Category {

    private int id;
    private String name;

    public Category(Category category) {
        this.id=category.getId();
        this.name=category.getName();
    }

    public Category(String category) {
        this.name=category;
    }
}
