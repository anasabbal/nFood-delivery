package com.example.fooddelivery.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;



@Getter
@AllArgsConstructor
public enum ExceptionFactory {

    TECHNICAL_ERROR(0, HttpStatus.INTERNAL_SERVER_ERROR, "technical.error"),
    INVALID_PAYLOAD(1, HttpStatus.BAD_REQUEST, "invalid.request.payload"),
    // Domain :
    DOMAIN_NOT_FOUND(2, HttpStatus.NOT_FOUND, "domain.not.found"),

    // ADDRESS :
    ADDRESS_NOT_FOUND(3, HttpStatus.NOT_FOUND, "address.not.found"),

    // ORDER :
    ORDER_NOT_FOUND(3, HttpStatus.NOT_FOUND, "order.not.found"),

    // ORDER :
    PRODUCT_NOT_FOUND(3, HttpStatus.NOT_FOUND, "product.not.found"),
    // ORDER :
    EMAIL_ALREADY_EXIST(3, HttpStatus.CONFLICT, "email.already.exist"),

    ERROR_PASSWORD(3, HttpStatus.UNAUTHORIZED, "password.unauthorized");



    private final Integer code;
    private final HttpStatus status;
    private final String message;

    public ExceptionPayload get() {
        return new ExceptionPayload(code, status, message);
    }
}
