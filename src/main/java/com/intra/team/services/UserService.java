package com.intra.team.services;

import com.intra.team.dtos.UserProfileDTO;
import org.springframework.security.core.Authentication;


public interface UserService {
    UserProfileDTO getMyProfile(Authentication auth);
}

