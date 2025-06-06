package com.studentfeedbacksystem.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentfeedbacksystem.models.User;
import com.studentfeedbacksystem.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserDetailsController {
    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public ResponseEntity<Map<String, String>> getUserDetails(
        @RequestParam String username
    ) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", user.getUsername());
        userDetails.put("email", user.getEmail());
        userDetails.put("phoneNumber", user.getPhoneNumber());
        userDetails.put("role", user.getRole());

        return ResponseEntity.ok(userDetails);
    }
    
}