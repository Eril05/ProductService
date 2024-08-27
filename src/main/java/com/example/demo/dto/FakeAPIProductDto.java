package com.example.demo.dto;


import lombok.Data;

@Data
public class FakeAPIProductDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
