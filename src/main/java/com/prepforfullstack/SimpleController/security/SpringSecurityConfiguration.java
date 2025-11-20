package com.prepforfullstack.SimpleController.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    Function<String, String> encodePassword = input ->
        passwordEncoder().encode(input);

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user = User.builder().passwordEncoder(encodePassword).username("Mothofeela").password("@Mo2f").roles("user").build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
