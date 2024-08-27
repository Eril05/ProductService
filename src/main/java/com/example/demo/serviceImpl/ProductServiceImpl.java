package com.example.demo.serviceImpl;

import com.example.demo.dto.FakeAPIProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {



    private  RestTemplate restTemplate;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {

        ResponseEntity<List<FakeAPIProductDto>> response =restTemplate.
                exchange("https://fakestoreapi.com/products", HttpMethod.GET,null,
                        new ParameterizedTypeReference<List<FakeAPIProductDto>>(){});
        List<FakeAPIProductDto> listOfProducts=new ArrayList<>();
//        String response =restTemplate.
//        getForObject("https://fakestoreapi.com/products", String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//             listOfProducts = objectMapper.readValue(response, new TypeReference<List<FakeAPIProductDto>>() {
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        ArrayList<Product>products=new ArrayList<>();
         listOfProducts=response.getBody();
        if(listOfProducts.isEmpty()){
            return new ArrayList<>();
        }
        for(FakeAPIProductDto fakeAPIProductDto:listOfProducts){
                products.add(covertFakeAPIProductDtoToProduct(fakeAPIProductDto));
        }

        return products;
    }

    @Override
    public Product getProduct(Long userId) {

        FakeAPIProductDto fakeAPIProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/"+userId, FakeAPIProductDto.class);

        if(fakeAPIProductDto==null){
            return null;
        }

        Product product=covertFakeAPIProductDtoToProduct(fakeAPIProductDto);
        if(product==null){
            return new Product();
        }
        return product;
    }

    public Product covertFakeAPIProductDtoToProduct(FakeAPIProductDto fakeAPIProductDto){

        Product product = new Product();
        product.setId(fakeAPIProductDto.getId());
        product.setTitle(fakeAPIProductDto.getTitle());
        Category category = new Category(fakeAPIProductDto.getCategory());
        product.setCategory(category);
        product.setDescription(fakeAPIProductDto.getDescription());
        product.setPrice(fakeAPIProductDto.getPrice());
        product.setImage(fakeAPIProductDto.getImage());


        return product;

    }

    @Override
    public Product addProduct(Long userId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
