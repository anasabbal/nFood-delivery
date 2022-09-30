package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.command.CategoryFoodItemRequest;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.CategoryDTO;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.dto.mapper.CategoryMapper;
import com.example.fooddelivery.dto.mapper.FoodItemMapper;
import com.example.fooddelivery.model.Category;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final FoodItemMapper foodItemMapper;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable).map(categoryMapper::toDto));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCommand categoryCommand){
        final Category category = categoryService.create(categoryCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryMapper.toDto(category));
    }
    @PostMapping("/{categoryId}" + FOOD_ITEM)
    public ResponseEntity<FoodItemDto> addFoodItem(@PathVariable("categoryId") final String categoryId,
                                                   @RequestBody final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = categoryService.addFoodItem(categoryId, foodItemCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(foodItem.getId()).toUri();
        return ResponseEntity.created(uri).body(foodItemMapper.toProductDto(foodItem));
    }
    @DeleteMapping("/remove" + FOOD_ITEM)
    public ResponseEntity<Void> removeFoodItem(@RequestBody final CategoryFoodItemRequest categoryFoodItemRequest){
        categoryService.removeFoodItem(categoryFoodItemRequest);
        return ResponseEntity.noContent().build();
    }
}
