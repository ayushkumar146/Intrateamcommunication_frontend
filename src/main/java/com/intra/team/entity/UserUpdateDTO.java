package com.intra.team.entity;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @NotBlank(message="Email required")
    @Email(message="Invalid email")
    private String email;

    private String phoneNumber;
}
