package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {

    Customer createCustomer(final CustomerCommand customerCommand);
    Page<CustomerDto> getAll(Pageable pageable);
    Customer findById(String customerId);
    Customer update(String customerId, CustomerCommand customerCommand);
    Customer deleteCustomer(String customerId);
    Customer getCurrentUser();
}
