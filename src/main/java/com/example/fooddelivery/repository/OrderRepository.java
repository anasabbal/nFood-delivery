package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.BillingAddress;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity<BillingAddress, ShippingAddress>, String> {
}
