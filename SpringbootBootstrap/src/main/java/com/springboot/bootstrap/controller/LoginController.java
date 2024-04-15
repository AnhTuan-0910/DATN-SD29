package com.springboot.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginPage() {
        return "auth-login";
    }

    @RequestMapping("/giao_dich")
    public String giaoDich() {
        return "/pages/giao_dich";
    }

    @RequestMapping("/home")
    public String home() {
        return "/pages/landing_page";
    }
}
