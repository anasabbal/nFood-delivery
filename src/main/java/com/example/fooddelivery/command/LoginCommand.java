package com.example.fooddelivery.command;


import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginCommand implements Validate {

    private final String email;
    private final String password;


    @Override
    public void validate() {

    }
}
