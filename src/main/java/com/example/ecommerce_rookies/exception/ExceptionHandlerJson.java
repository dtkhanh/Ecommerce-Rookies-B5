package com.example.ecommerce_rookies.exception;

import com.example.ecommerce_rookies.exception.product.NotFoundProduct;
import com.example.ecommerce_rookies.exception.product.NotFoundProductByCategory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerJson extends ResponseEntityExceptionHandler {

    private String sendErrorJson(HttpStatus httpStatus , Exception exception){
        JSONObject jsObject = new JSONObject() ;
        try {
            jsObject.put("status", httpStatus.value());
            jsObject.put("error", httpStatus.getReasonPhrase());
            jsObject.put("message", exception.getMessage().split(":")[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsObject.toString();
    }


    @ExceptionHandler(value = NotFoundProduct.class)
    public ResponseEntity<?> sendErrorNotFoundProductById(NotFoundProduct exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }

    @ExceptionHandler(value = NotFoundProductByCategory.class)
    public ResponseEntity<?> sendErrorNotFoundProductByCategory(NotFoundProductByCategory exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }




}
