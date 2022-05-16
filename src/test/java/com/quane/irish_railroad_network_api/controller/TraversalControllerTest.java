package com.quane.irish_railroad_network_api.controller;

import com.quane.irish_railroad_network_api.config.SecurityConfig;
import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.security.JwtAuthenticationFilter;
import com.quane.irish_railroad_network_api.service.TraversalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TraversalController.class)
// @WebMvcTest(controllers =
//         TraversalController.class,
//        excludeAutoConfiguration = {
//                SecurityConfig.class
//        })
@AutoConfigureMockMvc(addFilters = false)
class TraversalControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private TraversalController traversalController;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private TraversalService traversalService;

    @Test
    void getTraversalPathBFSTest() throws Exception {

        int startNode = 0;
        List<String> testList = Arrays.asList("Galway", "Ballinasloe", "Athenry");
        when(traversalService.getTraversalPathBFS(startNode)).thenReturn(testList);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/traverse/bfs/{startNode}/", startNode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[\"Galway\", \"Ballinasloe\", \"Athenry\"]"))
                .andExpect(status().isOk());
    }

    @Test
    void getTraversalPathDFS() throws Exception {

        int startNode = 0;
        List<String> testList = Arrays.asList("Galway", "Ballinasloe", "Athenry");
        when(traversalService.getTraversalPathDFS(startNode)).thenReturn(testList);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/traverse/dfs/{startNode}/", startNode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[\"Galway\", \"Ballinasloe\", \"Athenry\"]"))
                .andExpect(status().isOk());

    }

    @Test
    void getShortestPathBFS() throws Exception {

        int startNode = 0;
        int endNode = 3;
        List<String> testList = Arrays.asList("Galway", "Ballinasloe", "Athenry");
        when(traversalService.getShortestPathBFS(startNode, endNode)).thenReturn(testList);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/traverse/shortestpath/bfs/{startNode}/{endNode}/", startNode, endNode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[\"Galway\", \"Ballinasloe\", \"Athenry\"]"))
                .andExpect(status().isOk());

    }
}