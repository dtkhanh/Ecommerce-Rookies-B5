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


    private String roles;

    public JwtResponse(String accessToken, Long id, String username, String roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
