package com.example.ecommerce_rookies.ModelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private long id;

    private String name;

    public CategoryDTO(){}

    public CategoryDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
