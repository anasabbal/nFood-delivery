package com.example.fooddelivery.controller;



import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.dto.mapper.FoodItemMapper;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.service.foodItem.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.*;

@RestController
@RequestMapping(V1 + FOOD_ITEM)
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;
    private final FoodItemMapper foodItemMapper;

    @GetMapping
    public ResponseEntity<Page<FoodItemDto>> getFoodItems(Pageable pageable){

        return ResponseEntity.ok(foodItemService.getAllFoodItems(pageable));
    }
    @PostMapping
    public ResponseEntity<FoodItemDto> create(@RequestBody final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = foodItemService.createFoodItem(foodItemCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(foodItem.getId()).toUri();
        return ResponseEntity.created(uri).body(foodItemMapper.toProductDto(foodItem));
    }
    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItemDto> getOne(@PathVariable("foodItemId") final String foodItemId){
        final FoodItem foodItem = foodItemService.findOneFoodItem(foodItemId);

        return ResponseEntity.ok(foodItemMapper.toProductDto(foodItem));
    }
    @PutMapping("/{foodItemId}")
    public ResponseEntity<FoodItemDto> update(@PathVariable("foodItemId")final String foodItemId,
                                                     final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = foodItemService.updateFoodItem(foodItemId, foodItemCommand);
        return ResponseEntity.ok(foodItemMapper.toProductDto(foodItem));
    }
    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<FoodItemDto> deleteById(@PathVariable("foodItemId")final String foodItemId){
        foodItemService.deleteById(foodItemId);
        return ResponseEntity.noContent().build();
    }
}
