package com.intra.team.dtos;


import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
