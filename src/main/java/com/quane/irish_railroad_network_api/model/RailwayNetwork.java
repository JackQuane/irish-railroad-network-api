package com.quane.irish_railroad_network_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RailwayNetwork {

    HashMap<Integer, String> stations = new HashMap<>();
    List<List<Boolean>> adjacencyMatrix = new ArrayList<>();

}
