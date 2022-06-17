package com.example.ecommerce_rookies.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private Infomation infomation;

    @OneToMany(mappedBy = "banUser",cascade = CascadeType.ALL)
    private Set<BanUser> banUsers;

    @OneToMany(mappedBy = "userRate", cascade = CascadeType.ALL)
    private Set<ProductComment> productComment;

    public User(){}

    public User(String name, String password, Roles roles, Infomation infomation, Set<BanUser> banUsers, Set<ProductComment> productComment) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.infomation = infomation;
        this.banUsers = banUsers;
        this.productComment = productComment;
    }
}
