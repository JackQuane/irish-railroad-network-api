package com.quane.irish_railroad_network_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.security.JwtAuthenticationFilter;
import com.quane.irish_railroad_network_api.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = AuthController.class)
@WebMvcTest(AuthController.class)
//@WebMvcTest(controllers =
//        AuthController.class,
//        excludeAutoConfiguration = {
//                SecurityConfig.class
//        })
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @InjectMocks
    private AuthController authController;

    @MockBean
    private AuthService authService;

    @Test
    public void signUp_ValidRegistrationRequest_Successful() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/signup/")
                        .content(asJsonString(new RegisterRequest("tom", "password")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void login_ValidLoginCredentials_Successful() throws Exception {
        LoginRequest loginRequest = new LoginRequest("tom", "password");
        when(authService.login(loginRequest)).thenReturn("exampleToken");

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/login/")
                        .content(asJsonString(new LoginRequest("tom", "password")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("exampleToken"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}