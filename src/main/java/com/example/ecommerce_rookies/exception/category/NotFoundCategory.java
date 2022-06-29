package com.example.ecommerce_rookies.exception.category;


public class NotFoundCategory extends RuntimeException {
    public NotFoundCategory(Long id) {  super("Can not find product with id: " + id );}
}
