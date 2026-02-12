package com.intra.team.service_impl;
import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.entity.Users;
import com.intra.team.mappers.UserMapper;
import com.intra.team.repository.UserRepository;
import com.intra.team.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileDTO getMyProfile(Authentication auth) {

        String username = auth.getName();   // 👈 from JWT filter
        System.out.println("username1"+username);
        Users user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toDTO(user);
    }
}


