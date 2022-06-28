package com.example.ecommerce_rookies.exception.product;

public class NotFoundProductByCategory extends  RuntimeException{

    public NotFoundProductByCategory(){
        super("Can not find product by category");
    }
}
