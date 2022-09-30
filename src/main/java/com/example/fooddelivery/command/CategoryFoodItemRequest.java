package com.example.fooddelivery.command;


import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryFoodItemRequest implements Validate {
    private final String categoryId;
    private final String foodItemId;

    @Override
    public void validate() {

    }
}
