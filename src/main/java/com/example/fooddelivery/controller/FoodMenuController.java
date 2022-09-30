package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.FoodMenuCommand;
import com.example.fooddelivery.dto.FoodMenuDTO;
import com.example.fooddelivery.dto.mapper.FoodMenuMapper;
import com.example.fooddelivery.model.FoodMenu;
import com.example.fooddelivery.service.foodmenu.FoodMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.FOOD_MENU;
import static com.example.fooddelivery.cons.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + FOOD_MENU)
@RequiredArgsConstructor
public class FoodMenuController {

    private final FoodMenuService foodMenuService;
    private final FoodMenuMapper foodMenuMapper;

    @GetMapping("/{foodMenuId}")
    public ResponseEntity<FoodMenuDTO> getOne(@PathVariable("foodMenuId") final String foodMenuId) {
        return ResponseEntity.ok(foodMenuMapper.toDto(foodMenuService.findFoodMenu(foodMenuId)));
    }

    @PostMapping
    public ResponseEntity<FoodMenuDTO> create(@RequestBody final FoodMenuCommand foodMenuCommand) {
        final FoodMenu foodMenu = foodMenuService.createFoodMenu(foodMenuCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(foodMenu.getId()).toUri();
        return ResponseEntity.created(uri).body(foodMenuMapper.toDto(foodMenu));
    }

    @DeleteMapping("/{foodMenuId}")
    public ResponseEntity<FoodMenuDTO> deleteOne(@PathVariable("foodMenuId") final String foodMenuId) {
        foodMenuService.deleteFoodMenu(foodMenuId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{foodMenuId}")
    public ResponseEntity<FoodMenuDTO> update(@PathVariable("foodMenuId") final String foodMenuId, @RequestBody final FoodMenuCommand foodMenuCommand){
        final FoodMenu foodMenu = foodMenuService.updateFoodMnu(foodMenuId, foodMenuCommand);
        return ResponseEntity.ok(foodMenuMapper.toDto(foodMenu));
    }
}
