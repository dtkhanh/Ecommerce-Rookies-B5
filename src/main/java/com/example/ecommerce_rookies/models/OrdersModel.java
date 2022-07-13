package com.example.ecommerce_rookies.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="total")
    private float total;

    @Column(name="quantity")
    private int quantity;

    @Column(name ="listProduct")
    private String listProduct;


    @ManyToOne
    @JoinColumn(name="idAccount")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name="idCart")
    @JsonIgnore
    private Cartmodel cartmodel;





}
