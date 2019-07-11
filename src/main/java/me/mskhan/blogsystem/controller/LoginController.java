package me.mskhan.blogsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showFormForLogin")
    public String showFormForLogin() {
        return "login-form";
    }
}
