package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Orders {
    private long id;
    private float total;
    private LocalDate orderdate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User users;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails;

    public Orders(){}

    public Orders(float total, LocalDate orderdate, User users, Set<OrderDetails> orderDetails) {
        this.total = total;
        this.orderdate = orderdate;
        this.users = users;
        this.orderDetails = orderDetails;
    }
}
