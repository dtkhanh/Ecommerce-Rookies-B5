package com.example.ecommerce_rookies.modelDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private float price;

    private List<String> image;
    private String categoryid;
    private float rating;


    public ProductDTO(String name, String description, float price, List<String> image, String categoryid,float rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categoryid = categoryid;
        this.rating = rating;
    }

    public ProductDTO() {

    }
}
