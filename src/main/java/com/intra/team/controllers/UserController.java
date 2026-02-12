package com.intra.team.controllers;

import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfileDTO profile(Authentication authentication) {
        return userService.getMyProfile(authentication);
    }
}

