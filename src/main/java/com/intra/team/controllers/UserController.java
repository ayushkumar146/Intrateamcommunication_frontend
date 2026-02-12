package com.intra.team.controllers;

import com.intra.team.dtos.PasswordUpdateDTO;
import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.entity.UserUpdateDTO;
import com.intra.team.services.UserService;
import jakarta.validation.Valid;
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

    @PutMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfileDTO updateProfile(
            Authentication authentication,
            @Valid @RequestBody UserUpdateDTO dto) {

        return userService.updateMyProfile(authentication, dto);
    }

    @DeleteMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String deleteProfile(Authentication authentication) {

        userService.deleteMyAccount(authentication);
        return "Account deleted successfully";
    }

    @PutMapping("/password")
    @PreAuthorize("hasRole('USER')")
    public UserProfileDTO changePassword(
            Authentication auth,
            @Valid @RequestBody PasswordUpdateDTO dto) {

        return userService.changePassword(auth, dto);
    }
}

