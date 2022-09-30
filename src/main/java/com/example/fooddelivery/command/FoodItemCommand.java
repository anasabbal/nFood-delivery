package com.example.fooddelivery.command;

import com.example.fooddelivery.enums.FoodOrCategoryStatus;
import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@RequiredArgsConstructor
public class FoodItemCommand implements Validate {
    private final String name;
    private final Double price;
    private final Integer quantity;

    @Override
    public void validate() {
    }
}
