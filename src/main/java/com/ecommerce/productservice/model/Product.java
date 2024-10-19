package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {

//    @Id
//    private Long id;
    private String title;
    private double price;
    @ManyToOne( cascade = CascadeType.PERSIST)
    private Category category;
    private String description;
    private String image;
}
