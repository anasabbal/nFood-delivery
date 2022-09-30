package com.example.fooddelivery.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryDTO {
    private String id;
    private String name;
    private Set<FoodItemDto> foodItems;
    private String status;
}
