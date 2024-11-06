package com.ecommerce.productservice.serviceImpl;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.exception.InvalidProductIdException;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStore")
//@Primary
public class FakeStorDTOImpl implements ProductService {



    private  RestTemplate externalRestTemplate;
    private RedisTemplate redisTemplate;

    public FakeStorDTOImpl(RestTemplate externalRestTemplate, RedisTemplate redisTemplate) {
        this.externalRestTemplate = externalRestTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts(Integer pageNo, Integer pageSize)  {

        ProductDto[] listOfProducts;

        ResponseEntity<ProductDto[]> response =externalRestTemplate.

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
    public Product getProduct(Long productId)    {


        if(redisTemplate.opsForHash().get("PRODUCT","PRODUCT"+productId)!=null){
            return (Product) redisTemplate.opsForHash().get("PRODUCT","PRODUCT"+productId);
        }


        ProductDto productDto = externalRestTemplate.getForObject("https://fakestoreapi.com/products/"+productId, ProductDto.class);



        if(productDto==null){

            throw new InvalidProductIdException("The product id does not exist");
        }

        Product product=covertFakeAPIProductDtoToProduct(productDto);
        redisTemplate.opsForHash().put("PRODUCT","PRODUCT"+productId,product);
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
    public Product addProduct( Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {

        // you can a request like this to get the details without the response code

//        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, ProductDto.class);
//        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor(ProductDto.class, restTemplate.getMessageConverters());
//        ProductDto productDtoResponseEntity= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        HttpEntity<ProductDto> requestEntity = new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> productDtoResponseEntity=externalRestTemplate.exchange("https://fakestoreapi.com/products/"+id,HttpMethod.PUT,requestEntity,ProductDto.class);

        if(productDtoResponseEntity.getBody()!=null)
         return covertFakeAPIProductDtoToProduct(productDtoResponseEntity.getBody());
        return null;

    }

    @Override
    public Product updateProduct(Long userId,ProductDto productDto) {

        HttpEntity<ProductDto>requestEntity=new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> responseProductDto=externalRestTemplate.exchange("https://fakestoreapi.com/products/"+userId,HttpMethod.PATCH,requestEntity,ProductDto.class);

        if(responseProductDto.getBody()!=null)
            return  covertFakeAPIProductDtoToProduct(responseProductDto.getBody());
        return null;

    }
}
