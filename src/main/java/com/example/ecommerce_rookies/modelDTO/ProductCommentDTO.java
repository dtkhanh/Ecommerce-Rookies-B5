package com.example.ecommerce_rookies.modelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCommentDTO {

    private String id_account;
    private int rating;
    private String comment;
    private  String id_product;

    public ProductCommentDTO(){}

    public ProductCommentDTO(String id_Account, int rating, String comment, String id_name) {
        this.id_account = id_Account;
        this.rating = rating;
        this.comment = comment;
        this.id_product = id_name;
    }
}
