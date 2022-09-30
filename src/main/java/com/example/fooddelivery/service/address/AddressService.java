package com.example.fooddelivery.service.address;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AddressService <A extends Address>{

    <S extends AddressCommand> A create(final S payload, Class<A> aClass);
    <S extends AddressCommand> A updateAddress(String addressId, S payload);
    A findAddressById(String addressId);
    Page<A> getAddresses(Pageable pageable);
}
