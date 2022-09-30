package com.example.fooddelivery.model;




import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.command.OrderEntityCommand;
import com.example.fooddelivery.enums.Status;
import lombok.*;

import javax.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity <B, S extends Address> extends AbstractEntity {
    @OneToOne(targetEntity = BillingAddress.class, cascade = CascadeType.ALL)
    private B billingAddress;

    @OneToOne(targetEntity = ShippingAddress.class, cascade = CascadeType.ALL)
    private S shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orderEntity")
    private Set<FoodItem> foodItems;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "REJECT_REASON")
    private String rejectReason;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public static <B, S extends Address> OrderEntity<B, S> createOne(final OrderEntityCommand orderEntityCommand,
                                        final B billingAddress,
                                        final S shippingAddress){
        final OrderEntity<B, S> orderEntity = new OrderEntity<>();


        orderEntity.billingAddress = billingAddress;
        orderEntity.shippingAddress = shippingAddress;
        orderEntity.foodItems = createProduct(orderEntityCommand.getFoodItemCommands());

        return orderEntity;
    }
    public static Set<FoodItem> createProduct(Set<FoodItemCommand> foodItemCommands){
        return foodItemCommands.stream().map(FoodItem::createOne).collect(Collectors.toSet());
    }
    public FoodItem addFoodItem(final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = FoodItem.createOne(foodItemCommand);
        foodItem.linkToOrder(this);
        this.price +=  foodItem.getPrice();
        return foodItem;
    }
    public void removeFoodItem(FoodItem foodItem){
        this.foodItems.remove(foodItem);
        this.price -= foodItem.getPrice();
    }
    public void linkToShippingAddress(S address){
        this.shippingAddress = address;
    }
    public void linkToBillingAddress(B address){
        this.billingAddress = address;
    }
    public void update(final B billingAddress, S shippingAddress){
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
    @Override
    public void delete() {
        super.delete();
    }

}
