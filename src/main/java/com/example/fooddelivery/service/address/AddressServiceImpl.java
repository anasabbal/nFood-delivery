package com.example.fooddelivery.service.address;


import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionPayloadFactory;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.BillingAddress;
import com.example.fooddelivery.model.ShippingAddress;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl <T extends Address> implements AddressService<T>{

    private final AddressRepository<T> addressRepository;

    @Override
    @SneakyThrows
    public <S extends AddressCommand> T create(S payload, Class<T> aClass) {
        log.info("Begin creating address with payload {}", JSONUtil.toJSON(payload));
        T address = aClass.getDeclaredConstructor().newInstance();
        if(address instanceof BillingAddress) {
            BillingAddress.create(payload);
        }
        ShippingAddress.create(payload);
        return addressRepository.save(address);
    }

    @Override
    public <S extends AddressCommand> T updateAddress(String addressId, S payload) {
        T t = findAddressById(addressId);
        t.update(payload);
        return addressRepository.save(t);
    }

    @Override
    public T findAddressById(String addressId) {
        log.info("Begin fetching address with id {}", addressId);
        final T address = addressRepository.findById(addressId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.ADDRESS_NOT_FOUND.get())
        );
        log.info("Address with id {} fetched successful", addressId);
        return address;
    }

    @Override
    public Page<T> getAddresses(Pageable pageable) {
        return null;
    }

}
