package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, String> {
}
