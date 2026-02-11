package com.intra.team.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Admin dashboard data";
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public String stats() {
        return "System stats for admin";
    }
}
