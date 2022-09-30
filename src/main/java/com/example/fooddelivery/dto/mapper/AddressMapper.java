package com.example.fooddelivery.dto.mapper;


import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, OrderMapper.class})
public interface AddressMapper {
    AddressDto toAddressDto(Address address);
}
