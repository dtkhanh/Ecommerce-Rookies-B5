package com.example.ecommerce_rookies.exception.product;



public class NotFoundProduct extends RuntimeException {
    public NotFoundProduct(Long id) {
        super("Could not find product with id = " + id + ".");
    }

}