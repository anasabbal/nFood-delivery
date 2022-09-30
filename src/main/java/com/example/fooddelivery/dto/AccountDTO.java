package com.example.fooddelivery.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private String id;
    private CustomerDto customer;
    private String status;
    private FoodMenuDTO foodMenu;
}
