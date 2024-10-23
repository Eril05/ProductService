package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;


    @Test
    public void getAllProducts() {

        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        assertEquals(productController.getAllProducts(),new ArrayList<>());
    }

    @Test
    void getProduct() {

        when(productService.getProduct(2L)).thenReturn(new Product());

        assertEquals(productController.getProduct(2L),new Product());

    }
}