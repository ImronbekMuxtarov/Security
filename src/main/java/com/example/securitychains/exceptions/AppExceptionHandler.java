package com.example.securitychains.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice

public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<Map<String,String>> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach(error ->{
                Map<String ,String > errors = new HashMap<>();
                errors.put("field", ((FieldError) error).getField());
                errors.put("description",error.getDefaultMessage());
                errorList.add(errors);
        });
        return errorList;
    }
}
