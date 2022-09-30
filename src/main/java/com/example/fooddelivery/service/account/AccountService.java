package com.example.fooddelivery.service.account;

import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.LoginCommand;
import com.example.fooddelivery.model.Account;
import com.example.fooddelivery.payload.JwtResponse;

public interface AccountService {
    Account register(CustomerCommand customerCommand);
    JwtResponse login(LoginCommand loginCommand);
    Account getProfile();
}
