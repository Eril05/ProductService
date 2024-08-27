package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProducts ();

    public Product getProduct(Long userId);

    public Product addProduct(Long userId,Product product);

    public Product replaceProduct( Product product);

    public Product updateProduct( Product product);
}
