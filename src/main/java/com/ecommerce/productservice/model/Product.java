package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {


    private String title;
    private double price;
    @ManyToOne( cascade = CascadeType.PERSIST)
   // @JoinColumn(name="category_id")
    private Category category;
    private String description;
    private String image;
}
