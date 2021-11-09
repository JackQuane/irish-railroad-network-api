package com.quane.irish_railroad_network_api.controller;

import com.quane.irish_railroad_network_api.model.RailwayNetwork;
import com.quane.irish_railroad_network_api.service.TraversalService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/traverse/")
@AllArgsConstructor
public class TraversalController {

    private final TraversalService traversalService;

    @GetMapping("/bfs/{startNode}/")
    @ApiOperation("view a BFS traversal path of the Irish rail network starting from a specific station/node")
    public ResponseEntity<List<String>> getTraversalPathBFS(@PathVariable("startNode") int startNode) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(traversalService.getTraversalPathBFS(startNode));
    }

    @GetMapping("/dfs/{startNode}/")
    @ApiOperation("view a BFS traversal path of the Irish rail network starting from a specific station/node")
    public ResponseEntity<List<String>> getTraversalPathDFS(@PathVariable("startNode") int startNode) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(traversalService.getTraversalPathDFS(startNode));
    }

    @GetMapping("/shortestpath/bfs/{startNode}/{endNode}/")
    @ApiOperation("view the BFS shortest path of the Irish rail network from a startNode to an endNode")
    public ResponseEntity<List<String>> getShortestPathBFS(@PathVariable("startNode") int startNode, @PathVariable("endNode") int endNode) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(traversalService.getShortestPathBFS(startNode, endNode));
    }
}
