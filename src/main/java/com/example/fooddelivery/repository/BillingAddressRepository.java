package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.BillingAddress;
import org.springframework.stereotype.Repository;


@Repository
public interface BillingAddressRepository extends AddressRepository<BillingAddress> {
}
