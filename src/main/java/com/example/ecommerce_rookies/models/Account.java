package com.example.ecommerce_rookies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="Account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "roleId",nullable = false)
//    private Roles roles;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roleId",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> Roles = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<ProductComment> productComment;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private Set<BanUser> banUsers;

}
