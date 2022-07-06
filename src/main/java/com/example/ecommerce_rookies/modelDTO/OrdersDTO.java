package com.example.ecommerce_rookies.modelDTO;

import lombok.Getter;
import lombok.Setter;
;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrdersDTO {
    private Long id;

    private float total;

    private LocalDate orderdate;

    private Long id_account;

    private String name_account;

    private List<String> product;

    public OrdersDTO(){}

    public OrdersDTO(Long id, float total, LocalDate orderdate, Long id_account, String name_account, List<String> product) {
        this.id = id;
        this.total = total;
        this.orderdate = orderdate;
        this.id_account = id_account;
        this.name_account = name_account;
        this.product = product;
    }
}
