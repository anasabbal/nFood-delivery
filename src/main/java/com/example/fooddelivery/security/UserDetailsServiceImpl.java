package com.example.fooddelivery.security;


import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionPayloadFactory;
import com.example.fooddelivery.model.Account;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.payload.UserDetailsImpl;
import com.example.fooddelivery.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



/**
 * fetching user details from the database using the email.
 * The Spring Security Authentication Manager calls this method for getting the user
 * details from the database when authenticating the user details provided by the user.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Account  account = accountRepository.findAccountByCustomerEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CUSTOMER_NOT_FOUND.get())
        );
        return UserDetailsImpl.build(account);
    }
}
