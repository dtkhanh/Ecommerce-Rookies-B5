package com.example.ecommerce_rookies.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Information")


public class Infomation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idinfo;
    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="avatar")
    private String avatar;

    @Column(name="phonenumber")
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Account account;



}
