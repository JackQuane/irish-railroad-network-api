package com.quane.irish_railroad_network_api.controller;

import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.service.AuthService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    public void signUp_ValidRegistrationRequest_Successful() {
        RegisterRequest registerRequest = new RegisterRequest("tom", "password");
        doNothing().when(authService).signup(registerRequest);
        ResponseEntity<String> returnValue = authController.signup(registerRequest);
        verify(authService,times(1)).signup(registerRequest);
        assertEquals(returnValue, new ResponseEntity<>("User Registration Successful", HttpStatus.OK));
    }

    @Test
    public void login_ValidLoginCredentials_Successful() {
        LoginRequest loginRequest = new LoginRequest("tom", "password");
        when(authService.login(loginRequest)).thenReturn("exampleToken");
        String returnValue = authController.login(loginRequest);
        assertEquals(returnValue, "exampleToken");
    }
}