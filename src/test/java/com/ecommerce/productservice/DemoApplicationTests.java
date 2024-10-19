package com.ecommerce.productservice;

import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.CategoryRepo;
import com.ecommerce.productservice.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    void contextLoads() {


    }

    @Test
    void newTest(){

        Category category = categoryRepo.findById(1L).get();
        System.out.println(category.getName());
        System.out.println("fetch type");
        List<Product> products = category.getProducts();
        Product product = productRepo.doSomething();
       // System.out.println(products);
        System.out.println("done");
    }

}
