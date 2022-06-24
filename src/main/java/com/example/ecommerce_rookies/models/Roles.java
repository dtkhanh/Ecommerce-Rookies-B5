package com.example.ecommerce_rookies.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="roleName")
    private String rolename;

    @JsonIgnore
    @OneToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<Account> users;

}
