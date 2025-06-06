package com.studentfeedbacksystem.controllers;

import com.studentfeedbacksystem.services.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(adminDashboardService.getDashboardStats());
    }

    @GetMapping("/hostel-analytics")
    public ResponseEntity<Map<String, Object>> getHostelAnalytics() {
        return ResponseEntity.ok(adminDashboardService.getHostelAnalytics());
    }

    @GetMapping("/canteen-analytics")
    public ResponseEntity<Map<String, Object>> getCanteenAnalytics() {
        return ResponseEntity.ok(adminDashboardService.getCanteenAnalytics());
    }
}