package com.intra.team.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile() {
        return "User profile data";
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('USER')")
    public String orders() {
        return "User orders list";
    }
}
