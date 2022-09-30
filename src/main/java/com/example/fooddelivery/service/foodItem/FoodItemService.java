package com.example.fooddelivery.service.foodItem;

import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.model.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodItemService {

    FoodItem createFoodItem(FoodItemCommand foodItemCommand);

    FoodItem findOneFoodItem(String productId);

    Page<FoodItemDto> getAllFoodItems(Pageable pageable);
    void deleteById(String productId);
    FoodItem updateFoodItem(String productId, FoodItemCommand foodItemCommand);
}
