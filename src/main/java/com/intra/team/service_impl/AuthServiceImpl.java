package com.intra.team.service_impl;

import com.intra.team.dtos.LoginDTO;
import com.intra.team.dtos.RegisterDTO;
import com.intra.team.entity.Users;
import com.intra.team.exceptions.BadRequestException;
import com.intra.team.exceptions.ResourceNotFoundException;
import com.intra.team.exceptions.UnauthorizedException;
import com.intra.team.repository.UserRepository;
import com.intra.team.services.AuthService;
import com.intra.team.services.EmailOtpService;
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
    private final EmailOtpService emailOtpService;

    // ✅ REGISTER
    @Override
    public void register(RegisterDTO req) {

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new BadRequestException("Email already registered");
        }
        if (!emailOtpService.isVerified(req.getEmail())) {
            throw new UnauthorizedException("Email not verified");
        }

        Users user = new Users();
        user.setUsername(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone_number(req.getPhoneNumber());
        user.setRole("ROLE_USER");   // default
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);
    }

    // ✅ LOGIN
    @Override
    public String login(LoginDTO req) {

        Users user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(),user.getRole());
    }
}
