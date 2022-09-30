package com.example.fooddelivery.dto.mapper;


import com.example.fooddelivery.dto.CategoryDTO;
import com.example.fooddelivery.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public abstract CategoryDTO toDto(Category category);
}
