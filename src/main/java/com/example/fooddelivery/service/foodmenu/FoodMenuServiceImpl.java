package com.example.fooddelivery.service.foodmenu;


import com.example.fooddelivery.command.FoodMenuCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionPayloadFactory;
import com.example.fooddelivery.model.FoodMenu;
import com.example.fooddelivery.repository.FoodMenuRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FoodMenuServiceImpl implements FoodMenuService{

    private final FoodMenuRepository foodMenuRepository;

    @Override
    public FoodMenu createFoodMenu(FoodMenuCommand foodMenuCommand) {
        foodMenuCommand.validate();
        log.info("Begin creating food menu with payload {}", JSONUtil.toJSON(foodMenuCommand));
        final FoodMenu foodMenu = foodMenuRepository.save(FoodMenu.create(foodMenuCommand));
        log.info("Food menu with id {} created successfully", foodMenu.getId());
        return foodMenu;
    }

    @Override
    public FoodMenu findFoodMenu(String foodMenuId) {
        log.info("Begin fetching food menu with id {}", foodMenuId);
        return foodMenuRepository.findById(foodMenuId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.FOOD_MENU_NOT_FOUND.get())
        );
    }

    @Override
    public FoodMenu updateFoodMnu(String foodMenuId, FoodMenuCommand foodMenuCommand) {
        foodMenuCommand.validate();
        final FoodMenu foodMenu = findFoodMenu(foodMenuId);
        log.info("Begin updating id {} food menu with payload {}", foodMenuId, JSONUtil.toJSON(foodMenuCommand));
        foodMenu.update(foodMenuCommand);
        log.info("Food menu with id {} updated successfully", foodMenu.getId());
        return foodMenuRepository.save(foodMenu);
    }

    @Override
    public void deleteFoodMenu(String foodMenuId) {
        final FoodMenu foodMenu = findFoodMenu(foodMenuId);
        log.info("Begin deleting food menu with id {}", foodMenu.getId());
        foodMenu.delete();
        log.info("Food menu with id {} deleted successfully", foodMenu.getId());
        foodMenuRepository.save(foodMenu);
    }
}
