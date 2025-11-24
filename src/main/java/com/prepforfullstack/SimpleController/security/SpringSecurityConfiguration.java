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
//    Function<String, String> encodePassword = input ->
//        passwordEncoder().encode(input);

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user = User.builder().passwordEncoder(encodePassword).username("Mothofeela").password("@Mo2f").roles("user").build();
        UserDetails user1 = addUser("Mothofeela", "@M02f");
        UserDetails user2 = addUser("Lakabane","@Lucky");
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private UserDetails addUser(String username, String password){
        Function<String, String> encodePassword = input ->
                passwordEncoder().encode(input);
        return User.builder().passwordEncoder(encodePassword).username(username).password(password).roles("user").build();
    }
}
