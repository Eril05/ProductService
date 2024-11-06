package com.ecommerce.productservice.serviceImpl;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.CategoryRepo;
import com.ecommerce.productservice.repository.ProductRepo;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    private CategoryRepo categoryRepo;


    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public List<Product> getAllProducts(Integer pageNo, Integer pageSize) {

        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by("price").descending().and(Sort.by("title").ascending()));

        return productRepo.findAll(pageable).getContent();

    }

    @Override
    public Product getProduct(Long userId) {
         Optional<Product> product=productRepo.findById(userId);

        return product.orElseGet(Product::new);
    }

    @Override
    public Product addProduct(Product product) {
//        if(product.getCategory().getId()==null){
//            categoryRepo.save(product.getCategory());
//        }
        productRepo.save(product);
        return product;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        return null;
    }
}
