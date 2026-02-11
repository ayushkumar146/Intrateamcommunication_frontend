package com.intra.team.controllers;

import com.intra.team.services.EmailOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class EmailOtpController {

    private final EmailOtpService otpService;

    @PostMapping("/send-email-otp")
    public String sendOtp(@RequestParam String email) {
        otpService.sendOtp(email);
        return "OTP sent";
    }

    @PostMapping("/verify-email-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {

        otpService.verifyOtp(email, otp);
        return "Email verified";
    }
}
