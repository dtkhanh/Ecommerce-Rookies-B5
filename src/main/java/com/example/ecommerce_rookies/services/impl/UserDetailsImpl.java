package com.example.ecommerce_rookies.services.impl;

import com.example.ecommerce_rookies.models.Account;
import com.example.ecommerce_rookies.models.Roles;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl implements  UserDetails{
    private static final long serialVersionUID = 1L;

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Account user){
        Set<Roles> Roles = new HashSet<>();
        Collection<?> authorities = Set.of(new SimpleGrantedAuthority(user.getRoles().getRolename()));
        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                (Collection<? extends GrantedAuthority>) authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
