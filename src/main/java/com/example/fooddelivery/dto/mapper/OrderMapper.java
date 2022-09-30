package com.example.fooddelivery.dto.mapper;

import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.dto.BillingAddressDTO;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.ShippingAddressDTO;
import com.example.fooddelivery.model.BillingAddress;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.model.ShippingAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CustomerMapper.class, FoodItemMapper.class})
public interface OrderMapper {
    OrderDto<BillingAddressDTO, ShippingAddressDTO> toOrderDto(OrderEntity<BillingAddress, ShippingAddress> orderEntity);
}
