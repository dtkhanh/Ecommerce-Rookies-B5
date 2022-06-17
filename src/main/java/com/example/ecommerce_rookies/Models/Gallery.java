package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name ="Gallery")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product",nullable = false)
    private Product product;

    @Column(name = "image")
    private String image;

    public Gallery( ){}


    public Gallery(Product product, String image) {
        this.product = product;
        this.image = image;
    }
}
