package com.example.fooddelivery.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
public class JwtResponse {
    private String id;
    private String token;
    private String email;
    private List<String> roles;
    private String status;

    public JwtResponse(String id, String token, String email, List<String> roles, String status) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.roles = roles;
        this.status = status;
    }

}
