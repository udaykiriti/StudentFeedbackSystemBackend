package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.dto.UserProfileUpdateDTO;
import com.studentfeedbacksystem.models.User;
import com.studentfeedbacksystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void sendPasswordResetEmail(String email) {
        User user = findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String token = tokenService.generateTokenForUser(user);
        String resetLink = "http://localhost:3000/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(email, resetLink);
    }

    public void resetPassword(String token, String newPassword) {
        User user = tokenService.validateTokenAndGetUser(token);
        if (user == null) {
            throw new RuntimeException("Invalid or expired token.");
        }
        user.setPassword(newPassword);
        saveUser(user);
        tokenService.invalidateToken(token); 
    }

    public User updateUserProfile(String username, UserProfileUpdateDTO updateDTO, MultipartFile profilePicture) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setEmail(updateDTO.getEmail());
        user.setPhoneNumber(updateDTO.getPhoneNumber());
        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
            user.setPassword(updateDTO.getNewPassword());
        }
        if (profilePicture != null && !profilePicture.isEmpty()) {
            if (profilePicture.getSize() > 5 * 1024 * 1024) { // 5MB limit
                throw new RuntimeException("File size too large");
            }
            String contentType = profilePicture.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Invalid file type");
            }
            user.setProfilePicture(profilePicture.getBytes());
        }
        return userRepository.save(user);
    }
    public byte[] getUserProfilePicture(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.getProfilePicture() : null;
    }

    public User updateUserField(String username, String field, String value) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        switch (field.toLowerCase()) {
            case "email":
                user.setEmail(value);
                break;
            case "phonenumber":
                user.setPhoneNumber(value);
                break;
            default:
                throw new RuntimeException("Invalid field: " + field);
        }

        return userRepository.save(user);
    }

    public void changePassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}