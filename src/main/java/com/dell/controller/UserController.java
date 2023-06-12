package com.dell.controller;

import com.dell.dto.UserAssignment;
import com.dell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home() {
        return service.getUserDashBoard();
    }

    @GetMapping("user/technical_assessment")
    public String tech_assessmentPage(UserAssignment userAssignment) {
        System.out.println("api called");

        return "technical_assessment";
    }

    @GetMapping("user/behavioural_assessment")
    public String behav_assessmentPage(UserAssignment userAssignment) {
        System.out.println("api called");
        return "behavioural_assessment";
    }

    @PostMapping("/technical_assessment")
    public String technical_assessment(@ModelAttribute("userAssignment") UserAssignment userAssignment,
                                       BindingResult bindingResult) {
        System.out.println("api called---------");
       return service.technical_assessment(userAssignment);

    }

}
