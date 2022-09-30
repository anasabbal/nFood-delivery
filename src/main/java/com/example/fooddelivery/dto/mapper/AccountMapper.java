package com.example.fooddelivery.dto.mapper;


import com.example.fooddelivery.dto.AccountDTO;
import com.example.fooddelivery.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract AccountDTO toDto(Account account);
}
