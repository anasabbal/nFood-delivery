package com.example.fooddelivery.dto;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddressDto {
    private String id;
    private String street;
    private String city;
    private String country;
    private CustomerDto customer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
