package com.quane.irish_railroad_network_api.controller;

import com.quane.irish_railroad_network_api.service.TraversalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraversalControllerTest {

    @InjectMocks
    private TraversalController traversalController;

    @Mock
    private TraversalService traversalService;

    @Test
    void getTraversalPathBFS() {
        int startNode = 0;
        List<String> test = Arrays.asList("Galway", "Ballinalsoe", "Athenry");
        when(traversalService.getTraversalPathBFS(startNode)).thenReturn(test);
        ResponseEntity<List<String>> returnValue = traversalController.getTraversalPathBFS(startNode);
        assertEquals(returnValue.getBody(), test);
    }

    @Test
    void getTraversalPathDFS() {

    }

    @Test
    void getShortestPathBFS() {

    }
}