package com.example.fooddelivery.payload;


import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token ;
    private  String email;
    private  String newPassword ;
}
