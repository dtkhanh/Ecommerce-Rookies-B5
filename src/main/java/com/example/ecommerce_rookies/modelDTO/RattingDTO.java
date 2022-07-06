package com.example.ecommerce_rookies.modelDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RattingDTO {
    private Long id;

    private String name;
    private int rating;
    private String comment;
    private String product;
    public RattingDTO(){}

    public RattingDTO(Long id, String id_account, int rating, String comment, String id_product) {
        this.id = id;
        this.name = id_account;
        this.rating = rating;
        this.comment = comment;
        this.product = id_product;
    }
}
