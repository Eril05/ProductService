package com.ecommerce.productservice.dto;


import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
