package com.example.ecommerce_rookies.modelDTO;

import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Cartmodel;
import com.example.ecommerce_rookies.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
public class OrderModelDTO {
    private long id;

    private float total;

    private int quantity;

    private List<ProductDTO> listProduct;

    private String account;

    public OrderModelDTO(long id, float total, int quantity, List<ProductDTO> listProduct, String account) {
        this.id = id;
        this.total = total;
        this.quantity = quantity;
        this.listProduct = listProduct;
        this.account = account;
    }

    public OrderModelDTO() {

    }
}
