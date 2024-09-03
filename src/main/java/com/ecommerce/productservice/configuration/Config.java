package com.ecommerce.productservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
//    @Bean
//    public RestTemplate restTemplate2() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//        return new RestTemplate(factory);
//    }

}
