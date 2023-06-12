package com.dell.controller;

import com.dell.dto.UserDtls;
import com.dell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session) {

        boolean isEmailPresent = userService.checkEmail(user.getEmail());
        if (isEmailPresent) {
            session.setAttribute("msg","Email Id already Exist");
        } else {
            UserDtls savedUser = userService.createUser(user);
            if (Objects.nonNull(savedUser)) {
                session.setAttribute("msg","Registration successful");
            } else {
                session.setAttribute("msg","Internal Server Error");
            }
        }
        return "redirect:/register";
    }
}
