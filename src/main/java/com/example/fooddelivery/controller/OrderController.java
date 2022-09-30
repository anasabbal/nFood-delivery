package com.example.fooddelivery.controller;



import com.example.fooddelivery.command.*;
import com.example.fooddelivery.dto.BillingAddressDTO;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.ShippingAddressDTO;
import com.example.fooddelivery.dto.mapper.AddressMapper;
import com.example.fooddelivery.dto.mapper.FoodItemMapper;
import com.example.fooddelivery.dto.mapper.OrderMapper;
import com.example.fooddelivery.model.*;
import com.example.fooddelivery.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping(V1 + ORDERS)
@RequiredArgsConstructor
public class OrderController <T extends Address>{

    private final OrderService<T> orderService;
    private final OrderMapper orderMapper;
    private final FoodItemMapper foodItemMapper;

    @GetMapping
    public ResponseEntity<Page<OrderDto<BillingAddressDTO, ShippingAddressDTO>>> getAll(Pageable pageable){
        return ResponseEntity.ok(orderService.getAll(pageable));
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") final String orderId){
        final OrderEntity order = orderService.findOne(orderId);

        return ResponseEntity.ok(orderMapper.toOrderDto(order));
    }
    @PostMapping("/{orderId}/billingAddress")
    public <S extends AddressCommand> ResponseEntity<T> addBillingAddressToOrder(@PathVariable("orderId") final String orderId,
                                                               @RequestBody final S addressCommand, Class<T> aClass){
        final T address = orderService.createAddress(orderId, addressCommand, aClass);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }
    @PostMapping("/{orderId}/shippingAddress")
    public <S extends AddressCommand> ResponseEntity<T> addShippingAddressToOrder(@PathVariable("orderId") String orderId,
                                                                @RequestBody final AddressCommand addressCommand, Class<T> aClass){
        final T address = orderService.createAddress(orderId, addressCommand, aClass);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderEntityCommand orderEntityCommand){
        final OrderEntity order = orderService.create(orderEntityCommand);
        return ResponseEntity.ok(orderMapper.toOrderDto(order));
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") String orderId,
                                                @RequestBody final UpdateOrderRequest updateOrderRequest){
        final OrderEntity order = orderService.update(orderId, updateOrderRequest);
        return ResponseEntity.ok(orderMapper.toOrderDto(order));
    }
    @PostMapping("/{orderId}" + FOOD_ITEM)
    public ResponseEntity<FoodItemDto> addFoodItem(@PathVariable("orderId") final String orderId, @RequestBody final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = orderService.addFoodItem(orderId, foodItemCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(foodItem.getId()).toUri();
        return ResponseEntity.created(uri).body(foodItemMapper.toProductDto(foodItem));
    }
    @PostMapping
    public ResponseEntity<Void> removeFoodItem(@RequestBody final OrderFoodItemRequest orderFoodItemRequest){
        orderService.removeFoodItemFormOrder(orderFoodItemRequest);
        return ResponseEntity.noContent().build();
    }
}