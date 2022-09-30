package com.example.fooddelivery.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FoodItemDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String type;
    private BigDecimal price;
}