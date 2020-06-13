package com.example.realestaterentalbackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/register", "/user/login").permitAll()
                .antMatchers("/user/updateUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/advert/addAdvert").hasRole("USER")
                .and()
                .csrf().disable();
    }
}
