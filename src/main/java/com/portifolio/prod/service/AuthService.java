package com.portifolio.prod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.prod.model.UserModel;
import com.portifolio.prod.dto.LoginDTO;

@Service
public class AuthService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public String login(LoginDTO loginDTO) {
        UserModel userModel = userService.authentic(loginDTO.getMail(), loginDTO.getPassword())
                .orElseThrow(() -> new RuntimeException("Mail or password invalid"));

        return jwtService.generateTokenWithUser(userModel);
    }
}
