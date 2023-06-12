package com.dell.service;

import com.dell.dto.UserAssignment;
import com.dell.dto.UserDtls;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);
    public String getUserDashBoard();

    public String assessmentPage();
    public String technical_assessment(UserAssignment userAssignment);
    public List<UserAssignment> getAllEmployees();

}
