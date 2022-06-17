package com.example.ecommerce_rookies.Models;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nameproduct")
    private String title;
    @Column(name = "price")
    private float price;
    @Column(name = "imageproduct")
    private  String imageproduct;

    @ManyToOne
    @JoinColumn(name = "category",nullable = false)
    private Category category;
    @Column(name = "description")
    private String description;

    @Column(name = "update_date")
    private LocalDate createdate;
    @Column(name = "create_date")
    private LocalDate updatedate;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private Set<Gallery> gallery;

    @OneToMany(mappedBy = "productRate", cascade = CascadeType.ALL)
    private Set<ProductComment> productComments;




    public Product(){ }

    public Product(String title, float price, String imageproduct, Category category, String description, LocalDate createdate, LocalDate updatedate, Set<Gallery> gallery, Set<ProductComment> productComments) {
        this.title = title;
        this.price = price;
        this.imageproduct = imageproduct;
        this.category = category;
        this.description = description;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.gallery = gallery;
        this.productComments = productComments;
    }
}
