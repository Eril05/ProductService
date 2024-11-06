package com.ecommerce.productservice.controller;


import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.dto.UserSignUpResponseDto;
import com.ecommerce.productservice.exception.InvalidProductIdException;
import com.ecommerce.productservice.exception.InvalidTokenException;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {



    ProductService productService;
    RestTemplate restTemplate;

    public ProductController(@Qualifier("fakeStore") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }


   // @GetMapping("getAll/{token}")
    @GetMapping("getAll/")
    public List<Product> getAllProducts (@RequestParam Integer pageNo, @RequestParam Integer pageSize) throws InvalidTokenException {

       // System.out.println("Hello");
    //    UserSignUpResponseDto response=
      //  restTemplate.getForObject("http://localhost:8080/users/validate/"+token, UserSignUpResponseDto.class);
//        if(response==null){
//            throw new InvalidTokenException("Authorization has failed");
//        }
        List<Product>products=productService.getAllProducts( pageNo,pageSize);

        if(products==null)
                return new ArrayList<Product>();
        return products;
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable(name="productId")Long userId) throws InvalidProductIdException {

        return  productService.getProduct(userId);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){

        return productService.addProduct(product);
    }

    @PutMapping ("/{productId}")
    public Product replaceProduct(@PathVariable("productId")Long id, @RequestBody ProductDto productDto){
        return productService.replaceProduct(id, productDto);
    }

    @PatchMapping("hello/{userId}")
    public Product updateProduct(@PathVariable("userId")Long userId, @RequestBody ProductDto productDto){
        return productService.updateProduct(userId, productDto);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
