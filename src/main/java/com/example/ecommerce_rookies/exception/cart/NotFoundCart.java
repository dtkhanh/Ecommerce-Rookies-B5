package com.example.ecommerce_rookies.exception.cart;

public class NotFoundCart extends RuntimeException {
    public NotFoundCart(Long id) {  super("Can not find cart with id: " + id );}

}
