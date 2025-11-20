package com.prepforfullstack.SimpleController.HelloWorld;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("username")
public class HelloWorldController {
//
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    public HelloWorldController(AuthenticationService authenticationService){
//        this.authenticationService = authenticationService;
//    }
//
//    @RequestMapping("greet")
//    @ResponseBody
//    public String greet(){
//        return "Hello Spring World!";
//    }
//
//    @RequestMapping("greet2")
//    public String greet2(@RequestParam String name){
//        System.out.println("Request parameter: " + name);
//        return "greet";
//    }
//
//    @RequestMapping("greet3")
//    public String greet3(@RequestParam String name, @RequestParam String surname, ModelMap model){
//        model.put("name",name);
//        model.put("surname",surname);
//        return "greetEL";
//    }

    @RequestMapping("/")
    public String welcomePage(ModelMap model){
        model.put("username", getLoggedInUser());
        return "index";
    }

    private String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return auth.getName();
    }

//    @RequestMapping(value = "login", method = {RequestMethod.POST})
//    public String login(@RequestParam String username, @RequestParam String password, ModelMap model){
//        model.put("username",username);
//        model.put("password",password);
//
//        if (this.authenticationService.authenticate(username, password)){
//            return "index";
//        }
//        return "login";
//    }
}
