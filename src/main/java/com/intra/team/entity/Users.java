package com.intra.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String email;
    @JsonIgnore
    private String password;
    private String role;

}