package com.example.ecommerce_rookies.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "roleId",nullable = false)
    private Roles roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<ProductComment> productComment;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private Set<BanUser> banUsers;

}
