package com.intra.team.repository;


import com.intra.team.entity.EmailOtpRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailOtpRepository
        extends MongoRepository<EmailOtpRecord, String> {

    Optional<EmailOtpRecord> findByEmailAndOtp(String email, String otp);

    Optional<EmailOtpRecord> findTopByEmailOrderByCreatedAtDesc(String email);

    boolean existsByEmailAndVerifiedTrue(String email);
    void deleteByEmail(String email);
}


