package com.example.ecommerce_rookies.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class JwtResponse {
    @Setter
    private String token;

    @Setter
    private String type = "Bearer";

    @Setter
    private Long id;

    @Setter
    private String username;

    @Setter
    private String name;

    @Setter
    private String email;

    private String roles;

    public JwtResponse(String token, String type, Long id, String username, String name, String email, String roles) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
