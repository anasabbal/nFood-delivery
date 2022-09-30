package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.dto.mapper.AddressMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class AddressController  <T extends Address>{

    private final AddressService<T> addressService;
    private final AddressMapper addressMapper;


    @ApiOperation("This method create address")
    public <C extends AddressCommand> ResponseEntity<AddressDto> create(@RequestBody final C addressCommand, Class<T> clazz) {
        return ResponseEntity.ok(addressMapper.toAddressDto(addressService.create(addressCommand, clazz)));
    }

    @ApiOperation("This method will be update an address by id")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable final String id) {
        return ResponseEntity.ok(addressMapper.toAddressDto(addressService.findAddressById(id)));
    }

    @ApiOperation("This method will be delete an address by id")
    public <C extends AddressCommand> ResponseEntity<AddressDto> update(@PathVariable final String id, @RequestBody final C addressCommand) {
        return ResponseEntity.ok(addressMapper.toAddressDto(addressService.updateAddress(id, addressCommand)));
    }

}
