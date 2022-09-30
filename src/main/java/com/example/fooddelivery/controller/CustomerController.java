package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.dto.mapper.CustomerMapper;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.service.customer.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static com.example.fooddelivery.cons.ResourcePath.CUSTOMER;
import static com.example.fooddelivery.cons.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("This method return all customers")
    public ResponseEntity<Page<CustomerDto>> getAllCustomer(Pageable pageable){
        return ResponseEntity.ok(customerService.getAll(pageable));
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("#customerId == authentication.principal.userId or hasAnyRole('USER' or 'ADMIN')")
    @ApiOperation("This method will return customer with id")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") String customerId){
        final Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customerMapper.toCustomerDto(customer));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("This method will create a customer")
    public ResponseEntity<CustomerDto> createOne(@RequestBody CustomerCommand customerCommand)throws NullPointerException{
        final Customer customer = customerService.createCustomer(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customerMapper.toCustomerDto(customer));
    }
    @PutMapping("/{customerId}")
    @PreAuthorize("#customerId == authentication.principal.userId or hasAnyRole('USER' or 'ADMIN')")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") String customerId,
                                                      @RequestBody final CustomerCommand customerCommand){
        final Customer customer = customerService.update(customerId, customerCommand);
        return ResponseEntity.ok().body(customerMapper.toCustomerDto(customer));
    }
    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
