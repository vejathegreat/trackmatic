package com.velaphi.trackmatic.models;

public class Edge {

    private final int startingPoint;
    private final int destination;
    private final int weight;

    public Edge(int startingPoint, int destination, int weight) {
        if (startingPoint < 0)   throw new IllegalArgumentException("Vertex names must be positive Integer");
        if (destination < 0)     throw new IllegalArgumentException("Vertex names must be positive Integer");
        if (weight < 0) throw new IllegalArgumentException("Weight must be positive");
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.weight = weight;
    }

    public int getStartingPoint() {
        return startingPoint;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
