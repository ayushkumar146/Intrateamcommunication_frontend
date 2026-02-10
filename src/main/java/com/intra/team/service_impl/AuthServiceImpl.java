package com.intra.team.service_impl;

import com.intra.team.dtos.LoginDTO;
import com.intra.team.dtos.RegisterDTO;
import com.intra.team.entity.Users;
import com.intra.team.repository.UserRepository;
import com.intra.team.services.AuthService;
import com.intra.team.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ✅ REGISTER
    @Override
    public void register(RegisterDTO req) {

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Users user = new Users();
        user.setUsername(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone_number(req.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);
    }

    // ✅ LOGIN
    @Override
    public String login(LoginDTO req) {

        Users user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
