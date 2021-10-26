package com.quane.irish_railroad_network_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TraversalService {

//    private final NetworkRepository networkRepository;

//    public List<String> getPathBFS(String startNode, String endNode) {
//        networkRepository.findAll();
//
//    }

    public List<String> getPathDFS(String startNode, String endNode) {

        return new ArrayList<String>(Collections.singleton("test"));
    }
}
