package com.example.fooddelivery.dto;

import com.example.fooddelivery.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
public class OrderDto<B, S> {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CustomerDto customer;
    private B billingAddress;
    private S shippingAddress;
    private Set<FoodItemDto> products;
    private Status status;
    private Double price;
    private String rejectReason;

}
