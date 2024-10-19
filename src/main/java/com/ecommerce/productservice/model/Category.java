package com.ecommerce.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category extends BaseModel {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product>products;
    private String name;

//    public Category(Category category) {
//        this.id=category.getId();
//        this.name=category.getName();
//    }

    public Category(String category) {
        this.name=category;
    }

    public Category() {

    }
}
