package com.example.ecommerce_rookies.models;

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
@Table(name = "orders")
public class Orders {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="total")
    private float total;

    @Column(name="oderdate")
    private LocalDate orderdate;

    @OneToOne
    @JoinColumn(name = "order_idUser")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    private Set<OrderDetails> orderDetails;


}
