package com.ecommerce.productservice.controller;


import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

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
    public Product replaceProduct(@PathVariable("userId")Long id, @RequestBody ProductDto productDto){
        return productService.replaceProduct(id, productDto);
    }

    @PatchMapping("hello/{userId}")
    public Product updateProduct(@PathVariable("userId")Long userId, @RequestBody ProductDto productDto){
        return productService.updateProduct(userId, productDto);
    }
}
