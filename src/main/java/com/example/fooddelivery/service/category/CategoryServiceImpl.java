package com.example.fooddelivery.service.category;


import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.command.CategoryFoodItemRequest;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionPayloadFactory;
import com.example.fooddelivery.model.Category;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.repository.CategoryRepository;
import com.example.fooddelivery.repository.FoodItemRepository;
import com.example.fooddelivery.service.foodItem.FoodItemService;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final FoodItemRepository foodItemRepository;
    private final FoodItemService foodItemService;


    @Override
    public Category findOneCategory(String categoryId) {
        log.info("Begin fetching category with id {}", categoryId);
        final Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CATEGORY_NOT_FOND.get())
        );
        log.info("Category with id {} fetched successfully", category.getId());
        return category;
    }

    @Override
    public Category create(CategoryCommand categoryCommand) {
        categoryCommand.validate();
        log.info("begin creating category with payload {}", JSONUtil.toJSON(categoryCommand));


        if(categoryCommand.getFoodItems() != null){
            final Set<FoodItem> foodItems = Category.createCategory(categoryCommand.getFoodItems());
            final Category category = categoryRepository.save(Category.create(categoryCommand));
           category.setFoodItems(foodItems);
           return category;
        }
        log.info("Category with id created successfully");
        return categoryRepository.save(Category.create(categoryCommand));
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAllByDeletedFalse(pageable);
    }

    @Override
    public FoodItem addFoodItem(String categoryId, FoodItemCommand foodItemCommand) {
        foodItemCommand.validate();
        final Category category = findOneCategory(categoryId);
        log.info("Begin adding food Item with payload {} to category with id {}", JSONUtil.toJSON(foodItemCommand), category.getId());
        final FoodItem foodItem = foodItemRepository.save(category.addFoodItem(foodItemCommand));
        log.info("Food Item with id {} added successfully to category with id {}", foodItem.getId(), category.getId());
        return foodItem;
    }

    @Override
    public void removeFoodItem(CategoryFoodItemRequest categoryFoodItemRequest) {
        final FoodItem foodItem = foodItemService.findOneFoodItem(categoryFoodItemRequest.getFoodItemId());
        final Category category = findOneCategory(categoryFoodItemRequest.getCategoryId());

        log.info("Begin removing food Item with id {} from category with id {}", foodItem.getId(), category.getId());
        category.removeFoodItem(foodItem);
        foodItem.delete();
        foodItemRepository.save(foodItem);
        log.info("Food Item with id {} removed successfully from category with id {}", foodItem.getId(), category.getId());
    }
}
