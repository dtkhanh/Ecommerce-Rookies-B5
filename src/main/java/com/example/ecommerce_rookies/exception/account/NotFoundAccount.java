package com.example.ecommerce_rookies.exception.account;

public class NotFoundAccount extends RuntimeException{
    public NotFoundAccount(Long id){
        super("Can not find account with id: " + id );
    }
}
