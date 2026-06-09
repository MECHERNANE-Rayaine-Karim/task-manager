package com.Rayaine.task_manager.service;


import com.Rayaine.task_manager.model.User;
import com.Rayaine.task_manager.repository.UserRepository;
import com.Rayaine.task_manager.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public void register(String username, String password){
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public String login(String username, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );
        return jwtUtil.generateToken(username);
    }
}
