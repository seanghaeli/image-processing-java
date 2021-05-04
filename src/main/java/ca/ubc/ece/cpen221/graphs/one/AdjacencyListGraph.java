package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Edge;
import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Lists.
 *
 ******************************************************************************/

public class AdjacencyListGraph<T> implements Graph<T> {
    private ArrayList<Vertex<T>> vertices = new ArrayList<>();
    private ArrayList<Vertex<T>> verticesCopy = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<ArrayList<Vertex<T>>> representation = new ArrayList<>();

    /* AF: list contains element for each array, each element is a list of all
            neighbors of respective vertex. representation.get(i) is an ArrayList
            for ith vertex in vertices containing as its elements all vertices
            that have an edge connected to it from the ith vertex.
            representation.size() = vertices.size(),
            representation.get(i).size() = vertices.get(i).getNeighbors().size();

        RI: No duplicate vertices, each vertex is mapped as a list of all vertices
            that are connected to it. Vertex A is connected to Vertex B if there
            exists an edge that goes from Vertex A to Vertex B. */
    @Override
    public void addVertex(Vertex<T> v) {
        if (!vertices.contains(v)) {
            this.vertices.add(v);
            this.verticesCopy.add(v);
            representation.add(null);
        }
    }


    @Override
    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        this.edges.add(new Edge(v1,v2));
        int v1Ind = vertices.indexOf(v1);
        int v2Ind = vertices.indexOf(v2);
        representation.set(v1Ind, this.getNeighbors(v1));
        representation.set(v2Ind, this.getNeighbors(v2));
    }

    @Override
    public boolean edgeExists(Vertex<T> v1, Vertex<T> v2) {
        Edge findEdge = new Edge(v1,v2);
        for(Edge edge: this.edges)
            if(findEdge.equals(edge))
                return true;

        return false;
    }

    @Override
    public ArrayList<Vertex<T>> getNeighbors(Vertex<T> v) {
        ArrayList<Vertex<T>> neighbors = new ArrayList<Vertex<T>>();
        for(Vertex vertex: vertices)
            if(edgeExists(v, vertex))
                neighbors.add(vertex);


        return neighbors;
    }

    @Override
    public ArrayList<Vertex<T>> getVertices() {
        Collections.sort(verticesCopy, new LexographicalComparator());

        return verticesCopy;
    }

    //rep currently exposed!
//    public ArrayList<ArrayList<Vertex<T>>> getRepresentation(){ return representation; }
}
