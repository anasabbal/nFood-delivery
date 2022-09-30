package com.example.fooddelivery.enums;


import lombok.Getter;

@Getter
public enum FoodType {
    PIZZA,
    TACOS;
    private  Double pizza = 300.00;
    private  Double tacos = 400.00;

    FoodType() {

    }
    FoodType(Double pizza, Double tacos) {
        this.pizza = pizza;
        this.tacos = tacos;
    }

    public Double getPizza() {
        return pizza;
    }

    public Double getTacos() {
        return tacos;
    }
}
