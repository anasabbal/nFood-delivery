package com.example.fooddelivery.service.category;

import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.command.CategoryFoodItemRequest;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.model.Category;
import com.example.fooddelivery.model.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category findOneCategory(String categoryId);
    Category create(CategoryCommand categoryCommand);
    Page<Category> getAll(Pageable pageable);
    FoodItem addFoodItem(String categoryId, FoodItemCommand foodItemCommand);
    void removeFoodItem(CategoryFoodItemRequest categoryFoodItemRequest);
}
