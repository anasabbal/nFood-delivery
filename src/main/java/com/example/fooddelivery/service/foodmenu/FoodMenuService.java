package com.example.fooddelivery.service.foodmenu;

import com.example.fooddelivery.command.FoodMenuCommand;
import com.example.fooddelivery.model.FoodMenu;

public interface FoodMenuService {
    FoodMenu createFoodMenu(FoodMenuCommand foodMenuCommand);
    FoodMenu findFoodMenu(String foodMenuId);
    FoodMenu updateFoodMnu(String foodMenuId, FoodMenuCommand foodMenuCommand);
    void deleteFoodMenu(String foodMenuId);
}
