package com.example.ecommerce_rookies.exception.account;

public class NotFoundAccount extends RuntimeException{
    public NotFoundAccount(Long id){
        super("Can not find account with id: " + id );
    }
    public static class ExitsAccount extends RuntimeException {
        public ExitsAccount(Long id) {
            super("Can not ratting product because you have rated before !!!" + id );
        }

    }
}
