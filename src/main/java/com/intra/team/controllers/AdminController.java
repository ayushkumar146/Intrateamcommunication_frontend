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
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfileDTO adminProfile(Authentication authentication) {
        // Admins are users too! We can reuse the logic to get their profile.
        return userService.getMyProfile(authentication);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfileDTO updateProfile(
            Authentication authentication,
            @Valid @RequestBody UserUpdateDTO dto) {

        return userService.updateMyProfile(authentication, dto);
    }

    @DeleteMapping("/ADMIN")
    @PreAuthorize("hasRole('USER')")
    public String deleteProfile(Authentication authentication) {

        userService.deleteMyAccount(authentication);
        return "Account deleted successfully";
    }

    @PutMapping("/password")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfileDTO changePassword(
            Authentication auth,
            @Valid @RequestBody PasswordUpdateDTO dto) {

        return userService.changePassword(auth, dto);
    }

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
