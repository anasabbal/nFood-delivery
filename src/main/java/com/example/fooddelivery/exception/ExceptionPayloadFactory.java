package com.example.fooddelivery.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionPayloadFactory {

    TECHNICAL_ERROR(0, HttpStatus.INTERNAL_SERVER_ERROR, "technical.error"),
    INVALID_PAYLOAD(1, HttpStatus.BAD_REQUEST, "invalid.request.payload"),

    // Email
    EMAIL_ALREADY_EXIST(5, HttpStatus.FOUND, "email.already.found"),

    // CUSTOMER :
    CUSTOMER_NOT_FOUND(5, HttpStatus.NOT_FOUND, "customer.not.found"),

    ORDER_NOT_FOUND(5, HttpStatus.NOT_FOUND, "order.not.found"),
    FOOD_MENU_NOT_FOUND(6, HttpStatus.NOT_FOUND, "food.menu.not.found"),
    CATEGORY_NOT_FOND(7, HttpStatus.NOT_FOUND, "category.not.found"),
    PAYMENT_NOT_FOUND(8, HttpStatus.NOT_FOUND, "payment.not.found"),
    ADDRESS_NOT_FOUND(9, HttpStatus.NOT_FOUND, "address.not.found");



    private final Integer code;
    private final HttpStatus status;
    private final String message;

    public ExceptionPayload get() {
        return new ExceptionPayload(code, status, message);
    }
}
