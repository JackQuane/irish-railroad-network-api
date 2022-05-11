package com.quane.irish_railroad_network_api.service;

import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.model.User;
import com.quane.irish_railroad_network_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Test
    void signup() {

//        RegisterRequest registerRequest = new RegisterRequest("tom", "password");
//        authService.signup(registerRequest);
//
//
//        when(userRepository.save(any(User.class))).thenReturn("exampleToken");
//        doNothing().when(authService).signup(registerRequest);

    }

    @Test
    void login() {
    }
}