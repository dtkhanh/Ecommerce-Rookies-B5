package com.example.ecommerce_rookies.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Size(max = 100)
    private String name;


    @NotBlank
    @Size(max=50)
    private String email;

    @NotBlank
    @Size(min= 4 , max = 15)
    private String password;

    private String role;



}
