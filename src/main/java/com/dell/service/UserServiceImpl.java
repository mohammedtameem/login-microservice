package com.dell.service;

import com.dell.dto.UserAssignment;
import com.dell.dto.UserDtls;
import com.dell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${employee.service_url}")
    private String employeeServiceUrl;

    @Value("${admin.service_url}")
    private String adminServiceUrl;
    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate myRestTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDtls createUser(UserDtls user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return repository.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return repository.existsByEmail(email);
    }

    public String getUserDashBoard() {
        // JDK 11 Feature var keyword
        var users = myRestTemplate.getForObject(employeeServiceUrl + "/employeeDashboard", String.class);
        System.out.println(users);
        return users;
    }

    @Override
    public String assessmentPage() {
        var users = myRestTemplate.getForObject(employeeServiceUrl + "/employeeDashboard", String.class);
        System.out.println(users);
        return users;
    }

    @Override
    public String technical_assessment(UserAssignment userAssignment) {
        ResponseEntity<String> result = myRestTemplate.postForEntity(employeeServiceUrl + "/tech_assignment", userAssignment, String.class);
        System.out.println("----------------" + result.getBody());
        return result.getBody();
    }

    @Override
    public List<UserAssignment> getAllEmployees() {
        return myRestTemplate.exchange(
                adminServiceUrl + "/assisgnments",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserAssignment>>() {
                }).getBody();
    }


}
