package com.example.ecommerce_rookies.Models;

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
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="total")
    private float total;

    @Column(name="oderdate")
    private LocalDate orderdate;

    @OneToOne
    @JoinColumn(name = "order_idUser")
    private Account account;


}
