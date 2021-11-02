package com.quane.irish_railroad_network_api.service;

import com.quane.irish_railroad_network_api.model.RailwayNetwork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TraversalService {

    private final RailwayNetwork railwayNetwork;

    public List<String> getPathBFS(int startNode) {

        HashMap<Integer, String> stations = railwayNetwork.getStations();
        List<List<Boolean>> adjacencyMatrix = railwayNetwork.getAdjacencyMatrix();

        System.out.println(adjacencyMatrix.get(0));

        int matrixDimension = adjacencyMatrix.size();
        List<String> output = new ArrayList<>();
        boolean[] visited = new boolean[matrixDimension];
        Arrays.fill(visited, false);
        List<Integer> q = new ArrayList<>();
        q.add(startNode);

        // Set source as visited
        visited[startNode] = true;

        int vis = 0;
        while (!q.isEmpty())
        {
            vis = q.get(0);

            q.remove(q.get(0));
            //hashmap keys start from 1 not 0
            //adjacency matrix begins from 0,0 not 1,1
            output.add(stations.get(vis+1));

            // For every adjacent vertex to
            // the current vertex
            for(int i = 0; i < matrixDimension; i++)
            {
                if (adjacencyMatrix.get(vis).get(i) == true && (!visited[i]))
                {

                    // Push the adjacent node to
                    // the queue
                    q.add(i);

                    // Set
                    visited[i] = true;


                }
            }
        }

        return output;
    }

//    public List<String> getPathDFS(String startNode, String endNode) {
//
//        List<List<Boolean>> adjacencyMatrix = railwayNetwork.getAdjacencyMatrix();
//
//
//        return new ArrayList<String>(Collections.singleton("test"));
//    }

    public RailwayNetwork network() {
        return railwayNetwork;
    }
}
