package com.example.fooddelivery.model;


import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.enums.FoodOrCategoryStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "CATEGORIES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends AbstractEntity{

    @Column(name = "CATEGORY_NAME")
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FoodItem>  foodItems;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private FoodOrCategoryStatus status;

    public static Category create(final CategoryCommand categoryCommand){
        final Category category = new Category();

        category.name = categoryCommand.getName();
        category.foodItems = createCategory(categoryCommand.getFoodItems());
        category.status = FoodOrCategoryStatus.NEW;

        return category;
    }
    public FoodItem addFoodItem(final FoodItemCommand foodItemCommand){
        final FoodItem foodItem = FoodItem.createOne(foodItemCommand);

        foodItem.linkToCategory(this);
        return foodItem;
    }
    public static Set<FoodItem> createCategory(final Set<FoodItemCommand> foodItemCommands){
        return foodItemCommands.stream().map(FoodItem::createOne).collect(Collectors.toSet());
    }
    public void removeFoodItem(FoodItem foodItem){
        this.foodItems.remove(foodItem);
    }
    public void update(final CategoryCommand categoryCommand){
        this.name = categoryCommand.getName();
        this.foodItems = createCategory(categoryCommand.getFoodItems());
    }

    @Override
    protected void delete() {
        super.delete();
    }
}
