package com.example.ecommerce_rookies.modelDTO;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccountDto {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min= 4 , max = 15)
    private String password;


    private String address;

    private String phone;

    private String email;

    private  String avatar;

    public AccountDto(Long id, String username, String password, String address, String phone, String email, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
    }

    public AccountDto() {

    }
}
