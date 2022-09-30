package com.example.fooddelivery.repository;


import com.example.fooddelivery.model.ShippingAddress;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippingAddressRepository extends AddressRepository<ShippingAddress> {
}
