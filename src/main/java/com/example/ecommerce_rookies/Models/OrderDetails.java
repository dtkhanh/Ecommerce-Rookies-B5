package com.example.ecommerce_rookies.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
//
    @OneToOne
    @JoinColumn(name = "product_id" )
    private Product product;
//
//    public OrderDetails(float price, int number, Orders orders, Product product) {
//        this.price = price;
//        this.number = number;
//        this.orders = orders;
//        this.product = product;
//    }
}
