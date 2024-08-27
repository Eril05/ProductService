package com.example.demo.controller;


import com.example.demo.dto.FakeAPIProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {



    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public List<Product> getAllProducts (){

       // System.out.println("Hello");
        List<Product>products=productService.getAllProducts();
        if(products==null)
                return new ArrayList<Product>();
        return products;
    }

    @GetMapping("/{userId}")
    public Product getProduct(@PathVariable(name="userId")Long userId){

      Product product=  productService.getProduct(userId);
        if(product==null){
            return null;
        }
        return  product;
    }

    @PostMapping("/{userId}")
    public Product addProduct(@PathVariable(name="userId")Long userId,
                                 @RequestBody Product product){

        return new Product();
    }

    @PutMapping ("/{userId}")
    public Product replaceProduct(@RequestBody Product product){
        return new Product();
    }

    @PatchMapping("/{userId}")
    public Product updateProduct(@RequestBody Product product){
        return new Product();
    }
}
