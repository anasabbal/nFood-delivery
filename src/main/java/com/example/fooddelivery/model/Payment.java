package com.example.fooddelivery.model;


import com.example.fooddelivery.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "PAYMENT")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends AbstractEntity{
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "PAYMENT_TIME")
    private LocalDateTime paymentTime;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    public static Payment create(final Customer customer,
                                 LocalDateTime paymentTime,
                                 Double totalPrice,
                                 PaymentStatus status){
        final Payment payment = new Payment();

        payment.customer = customer;
        payment.paymentTime = paymentTime;
        payment.totalPrice = totalPrice;
        payment.status = status;
        payment.orderEntity = new OrderEntity();

        return payment;
    }
    public void linkToCustomer(Customer customer){
        this.customer = customer;
    }
}
