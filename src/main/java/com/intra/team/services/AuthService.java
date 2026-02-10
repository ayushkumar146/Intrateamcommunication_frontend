package com.intra.team.services;

import com.intra.team.dtos.LoginDTO;
import com.intra.team.dtos.RegisterDTO;

public interface AuthService {

    void register(RegisterDTO request);

    String login(LoginDTO request);
}