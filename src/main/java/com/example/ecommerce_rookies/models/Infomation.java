package com.example.ecommerce_rookies.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
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

    public Infomation(String username, String email, String address, String avatar, String phone, Account account) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
        this.phone = phone;
        this.account = account;
    }
}
