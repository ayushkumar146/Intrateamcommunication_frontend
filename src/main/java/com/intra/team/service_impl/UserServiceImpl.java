package com.intra.team.service_impl;
import com.intra.team.dtos.UserProfileDTO;
import com.intra.team.entity.UserUpdateDTO;
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

    @Override
    public UserProfileDTO updateMyProfile(Authentication auth,
                                          UserUpdateDTO dto) {
        System.out.println("update");
        Users user = getUserFromAuth(auth);
        System.out.println("user_update+"+ user);
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhone_number(dto.getPhoneNumber());
        }

        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

    @Override
    public void deleteMyAccount(Authentication auth) {
        Users user = getUserFromAuth(auth);
        userRepository.deleteById(user.getId());
    }

    // 🔹 helper
    private Users getUserFromAuth(Authentication auth) {
        // Ensure auth.getName() is actually returning "ayush@test.com"
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }
}


