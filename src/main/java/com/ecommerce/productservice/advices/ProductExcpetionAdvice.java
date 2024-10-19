package com.ecommerce.productservice.advices;

import com.ecommerce.productservice.dto.ExceptionDto;
import com.ecommerce.productservice.exception.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExcpetionAdvice {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidIdException(){

        ExceptionDto ex=new ExceptionDto();
        ex.setMessage("Invalid product id");
        ex.setDetails("this Id does not exist");

        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){

        ExceptionDto ex=new ExceptionDto();
        ex.setMessage("Arithmetic exception");

        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
