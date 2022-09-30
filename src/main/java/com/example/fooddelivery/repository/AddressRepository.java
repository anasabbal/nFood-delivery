package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository <T extends Address> extends JpaRepository<T, String> {
}
