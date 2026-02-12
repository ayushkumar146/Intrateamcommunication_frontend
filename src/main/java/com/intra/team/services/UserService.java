package com.intra.team.services;

import com.intra.team.dtos.PasswordUpdateDTO;
import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.entity.UserUpdateDTO;
import org.springframework.security.core.Authentication;


public interface UserService {
    UserProfileDTO getMyProfile(Authentication auth);
    UserProfileDTO updateMyProfile(Authentication auth, UserUpdateDTO dto);
    UserProfileDTO changePassword(Authentication auth,
                                  PasswordUpdateDTO dto);

    void deleteMyAccount(Authentication auth);
}

