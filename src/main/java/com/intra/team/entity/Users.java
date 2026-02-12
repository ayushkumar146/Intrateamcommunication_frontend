package com.intra.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class Users {

    @Id
    private String id;

    private String username;
    private String phone_number;

    @NotBlank(message="Email required")
    @Email(message="Invalid email")
    private String email;
    @JsonIgnore
    private String password;
    private String role;

}