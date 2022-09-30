package com.example.fooddelivery.command;


import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class OrderEntityCommand implements Validate {
    private String customer;
    private String billingAddress;
    private String shippingAddress;
    private Set<FoodItemCommand> foodItemCommands;

    @Override
    public void validate() {
        AssertValidation.isEmpty(customer);
        AssertValidation.isEmpty(billingAddress);
        AssertValidation.isEmpty(shippingAddress);
    }
}
