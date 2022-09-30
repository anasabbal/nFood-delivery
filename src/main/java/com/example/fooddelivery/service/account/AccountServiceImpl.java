package com.example.fooddelivery.service.account;


import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.LoginCommand;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionPayloadFactory;
import com.example.fooddelivery.model.Account;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.payload.JwtResponse;
import com.example.fooddelivery.payload.UserDetailsImpl;
import com.example.fooddelivery.repository.AccountRepository;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.security.UserDetailsServiceImpl;
import com.example.fooddelivery.service.customer.CustomerService;
import com.example.fooddelivery.util.JSONUtil;
import com.example.fooddelivery.util.TokenHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenHandler tokenHandler;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomerService customerService;

    @Override
    public Account register(CustomerCommand customerCommand) {
        log.info("Begin creating account with payload {}", JSONUtil.toJSON(customerCommand));

        if(!customerRepository.existsCustomerByEmail(customerCommand.getEmail())){
            final Customer customer = customerRepository.save(Customer.createOne(customerCommand));
            customer.setPassword(passwordEncoder.encode(customerCommand.getPassword()));
            final Account account = Account.create(customer);
            return accountRepository.save(account);
        }else{
            throw new BusinessException(ExceptionPayloadFactory.EMAIL_ALREADY_EXIST.get());
        }
    }
    @Override
    public JwtResponse login(LoginCommand loginCommand) {
        // If the authentication process is successful,
        // we can get Users information such as email, password, authorities from an Authentication
        //customerCommand.validate();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl base = (UserDetailsImpl) authentication.getPrincipal();

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginCommand.getEmail());
        List<String> roles = base.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String status = base.getStatus();

        final String token = tokenHandler.generateToken(authentication);
        log.info("token : {}", token);
        log.info("authority {}", userDetails.getAuthorities());
        return new JwtResponse(base.getUserId(), token, base.getUsername(), roles, status);
    }

    @Override
    public Account getProfile() {
        final Customer customer = customerService.getCurrentUser();
        return accountRepository.findByCustomer(customer);
    }
}
