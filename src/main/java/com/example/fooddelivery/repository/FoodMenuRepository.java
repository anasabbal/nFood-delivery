package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, String> {
}
