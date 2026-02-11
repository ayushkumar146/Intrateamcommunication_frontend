package com.intra.team.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "email_otps")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailOtpRecord {

    @Id
    private String id;

    private String email;
    private String otp;
    private Instant expiryTime;
    private boolean verified;
    private Instant createdAt;
}
