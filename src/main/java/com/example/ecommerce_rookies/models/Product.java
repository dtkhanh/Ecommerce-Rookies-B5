package com.example.ecommerce_rookies.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "product")
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

    @Column(name="ratting")
    private float ratting ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category", nullable = false)
    private Category category;

//    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductComment> productComments;

    @Column(name = "description")
    private String description;

    @Column(name = "update_date")
    private LocalDate createdate;
    @Column(name = "create_date")
    private LocalDate updatedate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "image",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Gallery> gallery;



}
