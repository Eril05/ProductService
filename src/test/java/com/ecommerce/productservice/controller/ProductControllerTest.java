//package com.ecommerce.productservice.controller;
//
//import com.ecommerce.productservice.model.Category;
//import com.ecommerce.productservice.model.Product;
//import com.ecommerce.productservice.repository.CategoryRepo;
//import com.ecommerce.productservice.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class ProductControllerTest {
//
////    @Autowired
////    private ProductController productController;
////
////    @MockBean
////    private ProductService productService;
//
////    @Autowired
////    private CategoryRepo categoryRepo;
////
////
////    @Test
////    void findByCategoryName(){
////        List<Category> categories= categoryRepo.findAll(Sort.by("name"));
////        System.out.println(categories);
////    }
//
//
////    @Test
////    public void getAllProducts() {
////
////        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
////
////        assertEquals(productController.getAllProducts(),new ArrayList<>());
////    }
//
////    @Test
////    void getProduct() {
////
////        when(productService.getProduct(2L)).thenReturn(new Product());
////
////        assertEquals(productController.getProduct(2L),new Product());
////
////    }
//}