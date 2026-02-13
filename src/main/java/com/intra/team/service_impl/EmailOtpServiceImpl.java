package com.intra.team.service_impl;

import com.intra.team.entity.EmailOtpRecord;
import com.intra.team.exceptions.BadRequestException;
import com.intra.team.exceptions.ResourceNotFoundException;
import com.intra.team.repository.EmailOtpRepository;
import com.intra.team.services.EmailOtpService;
import com.intra.team.services.EmailService;
import com.intra.team.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EmailOtpServiceImpl implements EmailOtpService {

    private final EmailOtpRepository repo;
    private final EmailService emailService;

    // ✅ SEND OTP
    @Override
    public void sendOtp(String email) {

        String otp = OtpUtil.generateOtp();

        // optional — keep only one active OTP
        repo.deleteByEmail(email);

        EmailOtpRecord record = new EmailOtpRecord();
        record.setEmail(email);
        record.setOtp(otp);
        record.setVerified(false);
        record.setExpiryTime(Instant.now().plusSeconds(300)); // 5 min
        record.setCreatedAt(Instant.now());

        repo.save(record);

        emailService.sendOtp(email, otp);
    }

    // ✅ VERIFY OTP (email + otp match)
    @Override
    public boolean verifyOtp(String email, String otp) {

        EmailOtpRecord record = repo
                .findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid OTP"));

        if (record.getExpiryTime().isBefore(Instant.now())) {
            throw new BadRequestException("OTP expired");
        }

        if (record.isVerified()) {
            return true;
        }

        record.setVerified(true);
        repo.save(record);

        return true;
    }

    // ✅ CHECK VERIFIED
    @Override
    public boolean isVerified(String email) {
        return repo.existsByEmailAndVerifiedTrue(email);
    }
}
