package com.quane.irish_railroad_network_api.service;

import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.model.User;
import com.quane.irish_railroad_network_api.repository.UserRepository;
import com.quane.irish_railroad_network_api.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void loginTest() {
        LoginRequest loginRequest = new LoginRequest("John", "password");
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()))).thenReturn(authentication);

        when(jwtProvider.generateToken(authentication)).thenReturn("testToken");

        assertEquals("testToken", authService.login(loginRequest));
    }
}