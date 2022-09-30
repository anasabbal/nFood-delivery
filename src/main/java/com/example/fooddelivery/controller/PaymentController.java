package com.example.fooddelivery.controller;


import com.example.fooddelivery.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.fooddelivery.cons.ResourcePath.PAYMENT;
import static com.example.fooddelivery.cons.ResourcePath.V1;

@RestController
@RequestMapping(V1 + PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
}
