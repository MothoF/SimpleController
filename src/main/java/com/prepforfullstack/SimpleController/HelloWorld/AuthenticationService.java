package com.prepforfullstack.SimpleController.HelloWorld;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password){
        return username.equals("Mothofeela") && password.equals("@Mo2f");
    }
}
