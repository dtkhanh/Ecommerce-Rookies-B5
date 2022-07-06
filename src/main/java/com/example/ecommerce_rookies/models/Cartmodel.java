package com.example.ecommerce_rookies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartmodel")
public class Cartmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="total")
    private float total;

    @Column(name="oderdate")
    private LocalDate orderdate;

    @ManyToOne
    @JoinColumn(name="idAccount")
    @JsonIgnore
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartmodel")
    private Set<OrderDetails> orderDetails;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartmodel")
    private Set<OrdersModel> ordersModels;



}
