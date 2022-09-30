package com.example.fooddelivery.service.foodItem;


import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.dto.mapper.FoodItemMapper;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.repository.FoodItemRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;


    @Override
    public Page<FoodItemDto> getAllFoodItems(Pageable pageable) {
        log.info("Begin fetching all food Items");
        Page<FoodItem> products = foodItemRepository.findAll(pageable);
        log.info("Food items with size {} fetched successfully", products.getTotalElements());
        return products.map(foodItemMapper::toProductDto);
    }

    @Override
    @Transactional
    public FoodItem createFoodItem(FoodItemCommand foodItemCommand) {
        foodItemCommand.validate();
        log.info("Begin creating food Item with payload {}", JSONUtil.toJSON(foodItemCommand));
        final FoodItem foodItem = foodItemRepository.save(FoodItem.createOne(foodItemCommand));
        log.info("Food Item with id {} created successfully", foodItem.getId());
        return foodItem;
    }
    @Override
    public FoodItem findOneFoodItem(String productId) {
        log.info("Begin fetching food Item with id {}", productId);
        return foodItemRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.PRODUCT_NOT_FOUND.get()));
    }
    @Override
    public void deleteById(String productId) {
        final FoodItem foodItem = findOneFoodItem(productId);
        log.info("Begin deleting food Item with id {}", foodItem.getId());
        foodItem.delete();
        log.info("Food Item with id {} deleted successfully", foodItem.getId());
        foodItemRepository.save(foodItem);
    }
    @Override
    public FoodItem updateFoodItem(String productId, FoodItemCommand foodItemCommand) {
        final FoodItem foodItem = findOneFoodItem(productId);
        log.info("Begin updating id {} food Item with payload {}", productId, JSONUtil.toJSON(foodItemCommand));
        foodItem.update(foodItemCommand);
        log.info("Food Item with id {} updated successfully", foodItem.getId());
        return foodItemRepository.save(foodItem);
    }
}
