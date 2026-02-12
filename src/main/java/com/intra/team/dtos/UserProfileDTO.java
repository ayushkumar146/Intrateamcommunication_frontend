package com.intra.team.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDTO {

    private String username;
    private String email;
    private String phoneNumber;
    private String role;
}

