package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Customer s " +
            "WHERE s.email = ?1"
    )
    Boolean selectExistsEmail(String email);
    Optional<Customer> findByEmail(String email);
    Boolean existsCustomerByEmail(String email);

}
