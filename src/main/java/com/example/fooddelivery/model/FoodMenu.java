package com.example.fooddelivery.model;


import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.command.FoodMenuCommand;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "FOOD_MENU")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodMenu extends AbstractEntity{

    private String menuName;

    @OneToMany
    private Set<Category> categories;

    public static FoodMenu create(final FoodMenuCommand foodMenuCommand){
        final FoodMenu foodMenu = new FoodMenu();

        foodMenu.menuName = foodMenuCommand.getMenuName();
        foodMenu.categories = createCategory(foodMenuCommand.getCategories());

        return foodMenu;
    }
    public static Set<Category> createCategory(final Set<CategoryCommand> categoryCommands){
        return categoryCommands.stream().map(Category::create).collect(Collectors.toSet());
    }
    public void update(final FoodMenuCommand foodMenuCommand){
        this.menuName = foodMenuCommand.getMenuName();
        this.categories = createCategory(foodMenuCommand.getCategories());
    }

    @Override
    public void delete() {
        super.delete();
    }
}
