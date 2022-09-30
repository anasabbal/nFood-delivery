package com.example.fooddelivery.model;


import com.example.fooddelivery.enums.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends AbstractEntity{

    @OneToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AccountStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FoodMenu foodMenu;

    public static Account create(final Customer customer){
        final Account account = new Account();

        account.customer = customer;
        account.status = AccountStatus.NEW;
        account.foodMenu = new FoodMenu();

        return account;
    }
}
