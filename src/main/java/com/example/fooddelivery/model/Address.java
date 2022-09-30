package com.example.fooddelivery.model;


import com.example.fooddelivery.command.AddressCommand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Address extends AbstractEntity{
    private String street;
    private String city;
    private String country;
    @ManyToOne(optional = false)
    private Customer customer;
    public Address(){

    }
    public Address(String street, String city, String country) {

        Assert.hasText(street, "Street must not be null or empty!");
        Assert.hasText(city, "City must not be null or empty!");
        Assert.hasText(country, "Country must not be null or empty!");

        this.street = street;
        this.city = city;
        this.country = country;
    }

    public static <S extends AddressCommand>  Address create(final S addressCommand){
        final Address address = new Address();
        address.street = addressCommand.getStreet();
        address.city = addressCommand.getCity();
        address.country = addressCommand.getCountry();

        return address;
    }
    public void update(final AddressCommand addressCommand){
        this.city = addressCommand.getCity();
        this.country = addressCommand.getCountry();
        this.street = addressCommand.getStreet();
    }

    @Override
    public void delete() {
        super.delete();
    }
}