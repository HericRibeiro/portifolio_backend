package com.portifolio.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

import com.portifolio.prod.dto.LoginDTO;
import com.portifolio.prod.service.JwtService;
import com.portifolio.prod.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {

        return userService.authentic(dto.getMail(), dto.getPassword())
            .map(user -> {
                String token = jwtService.generateTokenWithUser(user);
                return ResponseEntity.ok(token);
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Mail or password invalid"
            ));
    }
}
