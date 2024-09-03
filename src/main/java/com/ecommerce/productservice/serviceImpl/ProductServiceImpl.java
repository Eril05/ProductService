package com.ecommerce.productservice.serviceImpl;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.http.HttpEntity;
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

        ProductDto[] listOfProducts;

        ResponseEntity<ProductDto[]> response =restTemplate.

                exchange("https://fakestoreapi.com/products", HttpMethod.GET,null, ProductDto[].class);

        //  Option 2  use ParameterizedTypeReference

//                exchange("https://fakestoreapi.com/products", HttpMethod.GET,null,
//                        new ParameterizedTypeReference<List<FakeAPIProductDto>>(){});

        //Option 3 Use String class or List class

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
        if(listOfProducts.length == 0) return new ArrayList<>();
        for(ProductDto productDto :listOfProducts){
                products.add(covertFakeAPIProductDtoToProduct(productDto));
        }

        return products;
    }

    @Override
    public Product getProduct(Long userId) {

        ProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+userId, ProductDto.class);

        if(productDto ==null){
            return null;
        }

        Product product=covertFakeAPIProductDtoToProduct(productDto);
        if(product==null){
            return new Product();
        }
        return product;
    }

    public Product covertFakeAPIProductDtoToProduct(ProductDto productDto){

        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        Category category = new Category(productDto.getCategory());
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());


        return product;

    }

    @Override
    public Product addProduct(Long userId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {

        // you can a request like this to get the details without the response code

//        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, ProductDto.class);
//        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor(ProductDto.class, restTemplate.getMessageConverters());
//        ProductDto productDtoResponseEntity= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        HttpEntity<ProductDto> requestEntity = new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> productDtoResponseEntity=restTemplate.exchange("https://fakestoreapi.com/products/"+id,HttpMethod.PUT,requestEntity,ProductDto.class);

        if(productDtoResponseEntity.getBody()!=null)
         return covertFakeAPIProductDtoToProduct(productDtoResponseEntity.getBody());
        return null;

    }

    @Override
    public Product updateProduct(Long userId,ProductDto productDto) {

        HttpEntity<ProductDto>requestEntity=new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> responseProductDto=restTemplate.exchange("https://fakestoreapi.com/products/"+userId,HttpMethod.PATCH,requestEntity,ProductDto.class);

        if(responseProductDto.getBody()!=null)
            return  covertFakeAPIProductDtoToProduct(responseProductDto.getBody());
        return null;

    }
}
