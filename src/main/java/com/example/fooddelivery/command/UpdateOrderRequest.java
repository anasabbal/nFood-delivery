package com.example.fooddelivery.command;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateOrderRequest {
    private final String billingAddressId;
    private final String shippingAddressId;
}
