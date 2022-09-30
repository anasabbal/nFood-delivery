package com.example.fooddelivery.command;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderFoodItemRequest {
    private final String orderId;
    private final String foodItemId;
}
