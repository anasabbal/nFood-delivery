package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.command.LoginCommand;
import com.example.fooddelivery.dto.AccountDTO;
import com.example.fooddelivery.dto.mapper.AccountMapper;
import com.example.fooddelivery.model.Account;
import com.example.fooddelivery.payload.*;
import com.example.fooddelivery.service.account.AccountService;
import com.example.fooddelivery.util.TokenHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.net.URI;
import static com.example.fooddelivery.cons.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping(V1 + AUTH)
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final TokenHandler tokenHandler;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping(LOGIN)
    public ResponseEntity<JwtResponse> login(@RequestBody final LoginCommand loginCommand){
        return ResponseEntity.ok(accountService.login(loginCommand));
    }
    @PostMapping(REGISTER)
    public ResponseEntity<AccountDTO> signup(@RequestBody @Valid CustomerCommand customerCommand) {
        final Account account = accountService.register(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(accountMapper.toDto(account));
    }
    @PostMapping(LOGOUT)
    public ResponseEntity<?> logout(){
        ResponseCookie cookie = tokenHandler.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ResponseMsg("You've been signed out!"));
    }
}
