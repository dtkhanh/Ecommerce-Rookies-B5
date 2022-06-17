package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ProductComment")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User users;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product products;

    @Column(name="rating")
    private int rating;
    @Column(name="comment")
    private String comment;

    public ProductComment() {}
    public ProductComment(User users, Product products, int rating, String comment) {
        this.users = users;
        this.products = products;
        this.rating = rating;
        this.comment = comment;
    }
}
