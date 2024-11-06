package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.exception.InvalidProductIdException;
import com.ecommerce.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProducts (Integer pageNo, Integer pageSize) ;

    public Product getProduct(Long userId) ;

    public Product addProduct(Product product);

    public Product replaceProduct(Long id,ProductDto productDto);

    public Product updateProduct(Long id, ProductDto productDto);
}
