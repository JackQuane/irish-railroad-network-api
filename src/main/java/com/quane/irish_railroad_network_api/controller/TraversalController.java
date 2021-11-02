package com.quane.irish_railroad_network_api.controller;

import com.quane.irish_railroad_network_api.model.RailwayNetwork;
import com.quane.irish_railroad_network_api.service.TraversalService;
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

    @GetMapping("/bfs/{startNode}")
    public ResponseEntity<List<String>> getPathBFS(@PathVariable("startNode") int startNode) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(traversalService.getPathBFS(startNode));
    }
//
//    @GetMapping("/dfs/{startNode, endNode}")
//    public ResponseEntity<List<String>> getPathDFS(@PathVariable("startNode") String startNode, @PathVariable("endNode") String endNode) {
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(traversalService.getPathDFS(startNode, endNode));
//    }

    @GetMapping("/test/")
    public ResponseEntity<RailwayNetwork> testCont() {

        return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(traversalService.network());

    }
}