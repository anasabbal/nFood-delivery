package com.example.fooddelivery.model;


import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.enums.FoodOrCategoryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodItem extends AbstractEntity{


    @Column(name = "FOOR_ITEM_NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STATUS")
    private FoodOrCategoryStatus status;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    private OrderEntity orderEntity;
    @ManyToOne
    private Category category;

    public static FoodItem createOne(final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = new FoodItem();

        foodItem.name = foodItemCommand.getName();
        foodItem.price = foodItemCommand.getPrice();
        foodItem.status = FoodOrCategoryStatus.NEW;
        foodItem.quantity = foodItemCommand.getQuantity();

        return foodItem;
    }
    public void update(final FoodItemCommand foodItemCommand){
        this.name = foodItemCommand.getName();
        this.price = foodItemCommand.getPrice();
        this.quantity = foodItemCommand.getQuantity();
    }
    @Override
    public void delete() {
        super.delete();
    }

    public void linkToCategory(Category category) {
        this.category = category;
    }
    public void linkToOrder(OrderEntity orderEntity){
        this.orderEntity = orderEntity;
    }
}
