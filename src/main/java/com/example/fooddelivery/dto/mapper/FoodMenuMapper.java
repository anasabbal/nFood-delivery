package com.example.fooddelivery.dto.mapper;


import com.example.fooddelivery.dto.FoodMenuDTO;
import com.example.fooddelivery.model.FoodMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoodMenuMapper {
    public abstract FoodMenuDTO toDto(FoodMenu foodMenu);
}
