package com.example.ecommerce_rookies.Models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name="BanUser")
public class BanUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    @Column(name="time")
    private LocalDate date;

    public BanUser(){}

    public BanUser(User user, LocalDate date) {
        this.user = user;
        this.date = date;
    }
}
