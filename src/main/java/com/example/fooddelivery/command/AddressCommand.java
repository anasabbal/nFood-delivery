package com.example.fooddelivery.command;


import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCommand implements Validate {
    private String street;
    private String city;
    private String country;

    @Override
    public void validate() {
        AssertValidation.isEmpty(street);
        AssertValidation.isEmpty(city);
        AssertValidation.isEmpty(country);
    }
}
