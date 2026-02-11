package com.intra.team.services;

public interface EmailOtpService {

    void sendOtp(String email);

    boolean verifyOtp(String email, String otp);

    boolean isVerified(String email);
}
