package com.example.fooddelivery.service.order;

import com.example.fooddelivery.command.*;
import com.example.fooddelivery.dto.BillingAddressDTO;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.ShippingAddressDTO;
import com.example.fooddelivery.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService <T extends Address>{
    Page<OrderDto<BillingAddressDTO, ShippingAddressDTO>> getAll(Pageable pageable);
    <S extends AddressCommand> T createAddress(String orderId, S addressCommand, Class<T> aClass);
    FoodItem addFoodItem(String orderId, FoodItemCommand foodItemCommand);
    OrderEntity<BillingAddress, ShippingAddress> update(String orderId, UpdateOrderRequest updateOrderRequest);
    OrderEntity<BillingAddress, ShippingAddress> create(OrderEntityCommand orderEntityCommand);
    OrderEntity<BillingAddress, ShippingAddress> findOne(String orderId);
    void removeFoodItemFormOrder(OrderFoodItemRequest orderFoodItemRequest);
}
