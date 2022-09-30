package com.example.fooddelivery.command;


import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class FoodMenuCommand implements Validate {
    private final String menuName;
    private final Set<CategoryCommand> categories;


    @Override
    public void validate() {

    }
}
