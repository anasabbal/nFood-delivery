package com.example.fooddelivery.model;


import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CUSTOMERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends AbstractEntity{
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    //  a single email address cannot be used by multiple customers
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public static Customer createOne(final CustomerCommand customerCommand){
        final Customer customer = new Customer();

        customer.firstName = customerCommand.getFirstName();
        customer.lastName = customerCommand.getLastName();
        customer.email = customerCommand.getEmail();
        customer.password = customerCommand.getPassword();
        customer.role = Role.USER;
        customer.payment = Payment.create(customer, null, 0.0, null);
        return customer;
    }
    public void update(final CustomerCommand customerCommand){
        this.firstName = customerCommand.getFirstName();
        this.lastName = customerCommand.getLastName();
        this.email = customerCommand.getEmail();
        this.password = customerCommand.getPassword();
    }
    @Override
    public void delete() {
        super.delete();
    }
}