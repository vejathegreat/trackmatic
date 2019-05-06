package com.velaphi.trackmatic.trainsProblem.models;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Graph {
    private static final String[] VERTEX_LIST = {"A", "B", "C", "D", "E"};
    private final List<Edge>[] adj;
    private List<String> allPath;
    private int to;
    private int maxDistance;
    private int stops;
    private int routesCount;
    private int tripsCount;

    private Graph(int n) {
        if (n < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be positive");
        adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
    }

    public Graph(String inputGraph) {
        this(VERTEX_LIST.length);
        initializeGraph(inputGraph);
    }

    private void initializeGraph(String inputGraph){
        String[] inputArr = inputGraph.split(",");
        for (String s : inputArr) {
            s = s.trim();
            int from = getIndex(s.substring(0, 1));
            int to = getIndex(s.substring(1, 2));
            int weight = Integer.parseInt(s.substring(2));
            Edge e = new Edge(from, to, weight);
            addEdge(e);
        }
    }

    private void addEdge(Edge e) {
        int v = e.getStartingPoint();
        adj[v].add(e);
    }

    private List<Edge> adj(int v) {
        if (v < 0 || v >= VERTEX_LIST.length) throw new IndexOutOfBoundsException("vertex " + v + " not exists");
        return adj[v];
    }

    public String displayDistance(String route){
        int distance = calculateDistance(route);
        return (distance != -1) ? String.valueOf(distance) : "NO SUCH ROUTE";
    }

    private int calculateDistance(String route){
        if(route == null) throw new IllegalArgumentException("Route is wrong");
        int distance = 0;
        String[] vertex = route.trim().split("");
        int startingPoint, destination;

        for (int i = 0; i < vertex.length-1;) {
            boolean hasPath = false;
            startingPoint = getIndex(vertex[i++]);
            destination = getIndex(vertex[i]);
            List<Edge> edgeList = adj(startingPoint);
            for (Edge edge : edgeList)
                if (edge.getDestination() == destination) {
                    distance += edge.getWeight();
                    hasPath = true;
                    break;
                }
            if(!hasPath) return -1;
        }

        return distance;
    }


    public int calculateTripsCount(String startingPoing, String destination, Predicate<Integer> p, int stops){
        this.to = getIndex(destination);
        this.stops = stops;
        this.tripsCount = 0;
        int startIndex = getIndex(startingPoing);
        calculateTripsCount(startIndex, String.valueOf(startIndex), p);

        return tripsCount;
    }

    @SuppressLint("NewApi")
    private void calculateTripsCount(int from, String path, Predicate<Integer> p) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {

            String next = path + e.getDestination();
            int stopCount = next.length()-1;

            if (this.to == e.getDestination() && p.test(stopCount))
                tripsCount++;

            if(stopCount <= stops)
                calculateTripsCount(e.getDestination(), next, p);
        }
    }


    public int calculateShortestPath(String from, String to){
        allPath = new ArrayList<>();
        this.to = getIndex(to);
        int startIndex = getIndex(from);
        calculateShortestPath(startIndex, String.valueOf(startIndex));

        int shortestDistance = Integer.MAX_VALUE, currentDistance;
        for(String s: allPath){
            currentDistance = calculateDistance(s);
            if(shortestDistance > currentDistance)
                shortestDistance = currentDistance;
        }

        if(shortestDistance == Integer.MAX_VALUE) return 0;

        return shortestDistance;
    }

    private void calculateShortestPath(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {

            if (path.length()>1 && path.substring(1).contains(String.valueOf(e.getDestination()))) //checked visited or not
                continue;

            String next = path + e.getDestination();

            if (this.to == e.getDestination())
                allPath.add(getPathName(next));

            calculateShortestPath(e.getDestination(), next);
        }
    }


    public int calculateRoutesCount(String from, String to, int maxDistance){
        this.to = getIndex(to);
        this.maxDistance = maxDistance;
        this.routesCount = 0;
        int startIndex = getIndex(from);
        calculateRoutesCount(startIndex, String.valueOf(startIndex));

        return routesCount;
    }

    private void calculateRoutesCount(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {

            String next = path + e.getDestination();
            int distance = calculateDistance(getPathName(next));

            if (this.to == e.getDestination() && (distance < maxDistance))
                routesCount++;

            if(distance < maxDistance)
                calculateRoutesCount(e.getDestination(), next);
        }
    }


    private static int getIndex(String vertex) {
        int index = Arrays.binarySearch(VERTEX_LIST, vertex);
        if (index < 0)
            throw new IllegalArgumentException("Wrong input");

        return index;
    }

    private String getVertexName(int index) {
        if (index < 0 || index >= VERTEX_LIST.length)
            throw new IllegalArgumentException("Wrong index");

        return VERTEX_LIST[index];
    }

    private String getPathName(String path){
        String arr[] = path.trim().split("");
        String name = "";
        for(String v: arr)
            name += getVertexName(Integer.parseInt(v));

        return name;
    }
}
