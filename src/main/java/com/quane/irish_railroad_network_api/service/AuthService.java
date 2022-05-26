package com.quane.irish_railroad_network_api.service;

import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.exception.UserAlreadyExistsException;
import com.quane.irish_railroad_network_api.model.User;
import com.quane.irish_railroad_network_api.repository.UserRepository;
import com.quane.irish_railroad_network_api.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public void signup(RegisterRequest registerRequest) {

        String username = registerRequest.getUsername();

        if(userExists(username)) {
            throw new UserAlreadyExistsException("A user with the username '" + username + "' already exists");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            userRepository.save(user);
        }
    }

    public String login(LoginRequest loginRequest) {
        //authenticate login details and if match return JWT

        // .authenticate uses passwordEncoder.match() to check if password matches hash
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return token;
    }

    public boolean userExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
}
