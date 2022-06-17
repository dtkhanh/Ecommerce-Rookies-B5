package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="OrderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price")
    private float price;

    @Column(name= "quantity")
    private int number;

    @ManyToOne
    @JoinColumn(name="idOrders")
    private Orders orders;

    @OneToOne
    @JoinColumn(name = "idProduct" , referencedColumnName = "id")
    private Product product;

    public OrderDetails(){}

    public OrderDetails(float price, int number, Orders orders, Product product) {
        this.price = price;
        this.number = number;
        this.orders = orders;
        this.product = product;
    }
}
