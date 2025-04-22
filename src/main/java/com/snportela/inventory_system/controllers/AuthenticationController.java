package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.SecurityUser;
import com.snportela.inventory_system.domain.User;
import com.snportela.inventory_system.dtos.AuthenticationDto;
import com.snportela.inventory_system.dtos.LoginResponseDto;
import com.snportela.inventory_system.dtos.RegisterDto;
import com.snportela.inventory_system.infra.security.TokenService;
import com.snportela.inventory_system.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((SecurityUser) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto data) {

        Optional<User> foundUser =  userService.findByEmail(data.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        if(foundUser.isEmpty()) {
            User newUser = new User();

            newUser.setPassword(encryptedPassword);
            newUser.setEmail(data.email());
            newUser.setName(data.name());
            newUser.setPhone(data.phone());
            newUser.setRole(data.role());

            this.userService.save(newUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully.");
    }
}
