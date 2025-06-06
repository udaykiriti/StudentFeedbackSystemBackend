package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.dto.UserProfileUpdateDTO;
import com.studentfeedbacksystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "http://localhost:3000")
public class SettingsController {
    @Autowired
    private UserService userService;
    @PostMapping("/update-field")
    public ResponseEntity<String> updateField(
        @RequestParam String username,
        @RequestParam String field,
        @RequestParam String value
    ) {
        try {
            userService.updateUserField(username, field, value);
            return ResponseEntity.ok("Field updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
        @RequestParam String username,
        @RequestParam String currentPassword,
        @RequestParam String newPassword
    ) {
        try {
            userService.changePassword(username, currentPassword, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }	
    @PostMapping("/update-profile")
    public ResponseEntity<String> updateProfile(
        @RequestParam("username") String username,
        @RequestParam("email") String email,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("currentPassword") String currentPassword,
        @RequestParam(value = "newPassword", required = false) String newPassword,
        @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    ) {
        try {
            UserProfileUpdateDTO updateDTO = new UserProfileUpdateDTO();
            updateDTO.setUsername(username);
            updateDTO.setEmail(email);
            updateDTO.setPhoneNumber(phoneNumber);
            updateDTO.setCurrentPassword(currentPassword);
            updateDTO.setNewPassword(newPassword);

            // If no current password provided, you might want to add additional validation
            userService.updateUserProfile(username, updateDTO, profilePicture);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture(@RequestParam String username) {
        byte[] profilePicture = userService.getUserProfilePicture(username);
        
        if (profilePicture == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(profilePicture.length);

        return new ResponseEntity<>(profilePicture, headers, HttpStatus.OK);
    }
}