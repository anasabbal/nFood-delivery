package com.example.fooddelivery.exception;

import lombok.*;
import org.springframework.http.HttpStatus;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionPayload {

    private Integer code;
    private HttpStatus status;
    private String message;
    private Object[] args;
    private String reference;

    public ExceptionPayload(Integer code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}

