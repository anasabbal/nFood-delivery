package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Account;
import com.example.fooddelivery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByCustomerEmail(String email);
    Account findByCustomer(Customer customer);
}
