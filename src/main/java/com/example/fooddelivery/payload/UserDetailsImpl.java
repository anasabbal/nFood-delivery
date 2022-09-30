package com.example.fooddelivery.payload;

import com.example.fooddelivery.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private String userId;
    private String email;
    private String password;
    private GrantedAuthority authorities;
    private String status;
    public UserDetailsImpl(String userId, String email, String password, GrantedAuthority authorities, String status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
    }
    public static UserDetailsImpl build(Account account){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + account.getCustomer().getRole().name());
        return new UserDetailsImpl(
                account.getCustomer().getId(),
                account.getCustomer().getEmail(),
                account.getCustomer().getPassword(),
                authority,
                String.valueOf(account.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authorities);
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
