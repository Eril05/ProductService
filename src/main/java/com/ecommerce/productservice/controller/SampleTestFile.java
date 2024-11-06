package com.ecommerce.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SampleTestFile {

    @GetMapping("/conn")
    public String testingConnection(){

        return "Hello welcome to testing page";
    }

}
