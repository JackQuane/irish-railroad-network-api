package com.quane.irish_railroad_network_api.service;

import com.quane.irish_railroad_network_api.model.RailwayNetwork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TraversalService {

    private final RailwayNetwork railwayNetwork;

    public List<String> getTraversalPathBFS(int startNode) {
        HashMap<Integer, String> stations = railwayNetwork.getStations();
        List<List<Boolean>> adjacencyMatrix = railwayNetwork.getAdjacencyMatrix();

//        System.out.println(adjacencyMatrix.get(0));

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
                    visited[i] = true;
                }
            }
        }
        return output;
    }

    public List<String> getTraversalPathDFS(int startNode) {
        HashMap<Integer, String> stations = railwayNetwork.getStations();
        List<List<Boolean>> adjacencyMatrix = railwayNetwork.getAdjacencyMatrix();

        List<String> output = new ArrayList<>();
        int matrixDimension = adjacencyMatrix.size();
        boolean[] visited = new boolean[matrixDimension];
        Arrays.fill(visited, false);
        Stack<Integer> stk = new Stack<>();

        visited[startNode] = true;
        stk.push(startNode);
        int vis = 0;

        while(!stk.isEmpty()) {
            vis = stk.pop();
            output.add(stations.get(vis+1));

            for(int i = 0; i < matrixDimension; i++) {
                if(adjacencyMatrix.get(vis).get(i) == true && (!visited[i])) {
                    stk.push(i);
                    visited[i] = true;
                }
            }
        }
        return output;
    }

    public List<String> getShortestPathBFS(int startNode, int endNode) {
        List<List<Boolean>> adjacencyMatrix = railwayNetwork.getAdjacencyMatrix();
        int matrixDimension = adjacencyMatrix.size();
        boolean[] visited = new boolean[matrixDimension];
        Arrays.fill(visited, false);
        List<Integer> q = new ArrayList<>();
        q.add(startNode);
        //predecessor of node on path from startNode to endNode
        int[] predecessors = new int[matrixDimension];
        Arrays.fill(predecessors, -1);

        // Set source as visited
        visited[startNode] = true;
        int vis = 0;

        while (!q.isEmpty())
        {
            vis = q.get(0);

            q.remove(q.get(0));

            // For every adjacent vertex to
            // the current vertex
            for(int i = 0; i < matrixDimension; i++)
            {
                if (adjacencyMatrix.get(vis).get(i) == true && (!visited[i]))
                {
                    // Push the adjacent node to
                    // the queue
                    q.add(i);
                    visited[i] = true;

                    //setting predecessor node
                    predecessors[i] = vis;
                }
            }
        }

        return getPath(endNode, predecessors);
    }

    private List<String> getPath(int endNode, int[] predecessors) {

        HashMap<Integer, String> stations = railwayNetwork.getStations();

        List<Integer> path = new ArrayList<>();
        List<String> output = new ArrayList<>();

        int crawl = endNode;
        path.add(crawl);
        //crawling back from endNode to startNode
        while (predecessors[crawl] != -1) {
            path.add(predecessors[crawl]);
            crawl = predecessors[crawl];
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            //converting path from node numbers to station names
            output.add(stations.get(path.get(i)+1));
        }

        return output;
    }
}
