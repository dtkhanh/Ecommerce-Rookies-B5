package com.example.ecommerce_rookies.modelDTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDetailsDTO {
    private int number;

    private String id_product;

    public OrderDetailsDTO(){}

    public OrderDetailsDTO(int number, String id_product) {
        this.number = number;
        this.id_product = id_product;
    }
}
