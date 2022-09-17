package com.myservice.myproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Controller("/")
public class LoginController {

    @GetMapping("home")
    public String homePagge(HttpSession session){
        if(session.getAttribute("userName")!= null){
            System.out.println("name: " +session.getAttribute("userName"));
        }

        return "templates/html/index.html";
    }

    @PostMapping("/login")
    public String loginPage(String message, String error, String logout, Model model){
        if(error != null){
            model.addAttribute("error", "Invalid User name or Password");
        }
        if(logout != null){
            message = "You have been logged out successfully";
        }
//        List<Role> roles = roleDao.findAll();
//        moduleController.multiChoiceFormModule(model,"user_login",new User(), "/login", roles, message);
        return "/user_login";
//        return "user_login";
    }

}
