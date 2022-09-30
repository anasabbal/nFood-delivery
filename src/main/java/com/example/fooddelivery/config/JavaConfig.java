package com.example.fooddelivery.config;


import com.example.fooddelivery.security.AuthEntryPointJwt;
import com.example.fooddelivery.security.UserAuthenticationFilter;
import com.example.fooddelivery.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class JavaConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt pointJwt;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // for admin
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disabling csrf
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/v1/auth/**").permitAll()
                /*.antMatchers(HttpMethod.PUT, "/v1/customers/**").access("hasAuthority('ROLE_USER')")
                .antMatchers("/v1/customers/**", "/v1/order/**", "/products/**", "/v1/address/**").access("hasAuthority('ROLE_ADMIN')")*/
                .and()
                .exceptionHandling().authenticationEntryPoint(pointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // Expose authentication manager bean
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public UserAuthenticationFilter userAuthenticationFilter(){
        return new UserAuthenticationFilter();
    }
}
