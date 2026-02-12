package com.intra.team.mappers;

import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.entity.Users;

public class UserMapper {

    public static UserProfileDTO toDTO(Users user) {
        return new UserProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPhone_number(),
                user.getRole()
        );
    }
}