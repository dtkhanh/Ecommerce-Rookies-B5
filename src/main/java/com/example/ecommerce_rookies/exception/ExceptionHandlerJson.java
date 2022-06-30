package com.example.ecommerce_rookies.exception;

import com.example.ecommerce_rookies.exception.account.NotFoundAccount;
import com.example.ecommerce_rookies.exception.category.NotFoundCategory;
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


    @ExceptionHandler(value = NotFoundProductByCategory.NotFoundProduct.class)
    public ResponseEntity<?> sendErrorNotFoundProductById(NotFoundProductByCategory.NotFoundProduct exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }

    @ExceptionHandler(value = NotFoundProductByCategory.class)
    public ResponseEntity<?> sendErrorNotFoundProductByCategory(NotFoundProductByCategory exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }

    @ExceptionHandler(value = NotFoundCategory.class)
    public ResponseEntity<?> sendErrorNotFoundCategoryById(NotFoundCategory exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }

    @ExceptionHandler(value = NotFoundAccount.class)
    public ResponseEntity<?> sendErrorNotFoundAccountById(NotFoundAccount exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }

    @ExceptionHandler(value = NotFoundAccount.ExitsAccount.class)
    public ResponseEntity<?> sendErrorNotRattingProduct(NotFoundAccount.ExitsAccount exception){
        HttpStatus httpStatus = HttpStatus.EXPECTATION_FAILED;
        return new ResponseEntity<>(sendErrorJson(httpStatus,exception), httpStatus);
    }





}
