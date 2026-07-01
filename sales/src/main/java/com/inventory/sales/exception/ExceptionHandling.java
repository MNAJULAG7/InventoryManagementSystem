package com.inventory.sales.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> myResourceNotFoundException(ResourceNotFoundException e)
    {
        Map<String,Object> m = new HashMap<>();
        m.put("error","product");
        m.put("msg",e.getMessage());

        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }
}
