package com.example.fooddelivery.service.order;


import com.example.fooddelivery.command.*;
import com.example.fooddelivery.dto.BillingAddressDTO;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.ShippingAddressDTO;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.dto.mapper.OrderMapper;
import com.example.fooddelivery.model.*;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.FoodItemRepository;
import com.example.fooddelivery.service.address.AddressService;
import com.example.fooddelivery.service.foodItem.FoodItemService;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl <T extends Address> implements OrderService<T>{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final FoodItemService foodItemService;
    private final AddressService<T> addressService;
    private final FoodItemRepository foodItemRepository;

    @Override
    public Page<OrderDto<BillingAddressDTO, ShippingAddressDTO>> getAll(Pageable pageable) {

        log.info("Begin fetching orders");
        Page<OrderEntity<BillingAddress, ShippingAddress>> orderEntities = orderRepository.findAll(pageable);
        log.info("Orders with size {} fetched successfully", orderEntities.getTotalElements());
        return orderEntities.map(orderMapper::toOrderDto);
    }
    @Override
    public  OrderEntity<BillingAddress, ShippingAddress> create(OrderEntityCommand orderEntityCommand) {
        final T bilAddress =  orderEntityCommand.getBillingAddress() == null ? null: addressService.findAddressById(orderEntityCommand.getBillingAddress());
        final T shipAddress = orderEntityCommand.getShippingAddress() == null ? null : addressService.findAddressById(orderEntityCommand.getShippingAddress());
        log.info("Begin creating order with payload {}", JSONUtil.toJSON(orderEntityCommand));

        final OrderEntity<BillingAddress, ShippingAddress> order = orderRepository
                .save(OrderEntity.
                        createOne(orderEntityCommand, (BillingAddress) bilAddress,
                        (ShippingAddress) shipAddress));
        log.info("Order with id {} creating successfully", order.getId());
        return order;
    }
    @Override
    public OrderEntity<BillingAddress, ShippingAddress> findOne(String orderId) {
        log.info("Begin fetching order with id {}", orderId);
        final OrderEntity<BillingAddress, ShippingAddress> order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        log.info("Order with id {} fetched successfully",  order.getId());
        return order;
    }
    @Override
    public void removeFoodItemFormOrder(OrderFoodItemRequest orderFoodItemRequest) {
        final FoodItem foodItem = foodItemService.findOneFoodItem(orderFoodItemRequest.getFoodItemId());
        final OrderEntity<BillingAddress, ShippingAddress> orderEntity = findOne(orderFoodItemRequest.getOrderId());
        log.info("Begin removing food Item with id {} from order with id {}", foodItem.getId(), orderEntity.getId());
        orderEntity.removeFoodItem(foodItem);
        log.info("Food Item with id {} removed successfully from order with id {}", foodItem.getId(), orderEntity.getId());
        foodItem.delete();
    }
    @Override
    @SneakyThrows
    public <S extends AddressCommand> T createAddress(String orderId, S addressCommand, Class<T> aClass){

        return null;
    }
    @Override
    @Transactional
    public FoodItem addFoodItem(String orderId, FoodItemCommand foodItemCommand) {
        final OrderEntity<BillingAddress, ShippingAddress> order = findOne(orderId);
        log.info("Begin adding food Item with payload {} to order with id {}", JSONUtil.toJSON(foodItemCommand), order.getId());
        final FoodItem foodItem = foodItemRepository.save(order.addFoodItem(foodItemCommand));
        log.info("Food Item with id {} added successfully to order with id {}", foodItem.getId(), order.getId());
        return foodItem;
    }
    @Override
    public OrderEntity<BillingAddress, ShippingAddress> update(String orderId, UpdateOrderRequest updateOrderRequest) {
        log.info("Begin updating order with id {}", orderId);

        final T shippingAddress = addressService.findAddressById(updateOrderRequest.getShippingAddressId());
        final T billingAddress = addressService.findAddressById(updateOrderRequest.getBillingAddressId());

        final OrderEntity<BillingAddress, ShippingAddress> order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.ORDER_NOT_FOUND.get()));
        order.update((BillingAddress) billingAddress, (ShippingAddress) shippingAddress);
        log.info("Updating order with id {} successfully", orderId);

        return order;
    }
}
