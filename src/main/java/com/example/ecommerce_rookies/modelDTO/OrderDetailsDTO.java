package com.example.ecommerce_rookies.modelDTO;


import com.example.ecommerce_rookies.models.Orders;
import com.example.ecommerce_rookies.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
