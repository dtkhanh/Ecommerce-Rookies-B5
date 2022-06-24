package com.example.ecommerce_rookies.modelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private float price;

    private String image;
    private String categoryid;


    public ProductDTO(long id, String name, String description, float price, String image, String categoryid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categoryid = categoryid;
    }
}
