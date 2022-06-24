package com.example.ecommerce_rookies.models;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BanUser")
public class BanUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private Account account;

    @Column(name="time")
    private LocalDate date;

}
