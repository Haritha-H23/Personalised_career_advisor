package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.User;
import com.careeradvisor.backend.repo.UserRepository;
import com.careeradvisor.backend.security.JwtUtil;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {

    User dbUser = userRepository.findByEmail(user.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (encoder.matches(user.getPassword(), dbUser.getPassword())) {

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok().body(Map.of(
                "token", token,
                "email", dbUser.getEmail(),
                "name", dbUser.getName()
        ));
    }

    throw new RuntimeException("Invalid credentials");
}

}
