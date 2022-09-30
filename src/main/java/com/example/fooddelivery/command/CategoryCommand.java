package com.example.fooddelivery.command;


import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryCommand implements Validate {
    private final String name;
    private final Set<FoodItemCommand> foodItems;

    @Override
    public void validate() {

    }
}
