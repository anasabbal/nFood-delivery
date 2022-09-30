package com.example.fooddelivery.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FoodMenuDTO {
    private String id;
    private String menuName;
    private Set<CategoryDTO> categories;
}
