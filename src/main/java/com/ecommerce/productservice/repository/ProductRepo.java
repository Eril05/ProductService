package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query(value="select p from Product p where p.id =2")
    Product doSomething();
}
