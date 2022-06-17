package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @OneToOne(mappedBy = "idUser")
    private User user;

    public Infomation(String email ,String address, String avatar, String phone, User user){
        this.address=address;
        this.avatar=avatar;
        this.email=email;
        this.phone=phone;
        this.user=user;

    }

    public Infomation(){}


}
