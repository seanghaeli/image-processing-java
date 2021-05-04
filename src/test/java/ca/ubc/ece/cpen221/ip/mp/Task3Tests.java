package ca.ubc.ece.cpen221.ip.mp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.Algorithms;
import org.junit.Test;

import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;

import java.util.*;

public class Task3Tests {

    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    @Test
    public void sd_four_node(){
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);

        alg.addVertex(v1);
        alg.addVertex(v2);
        alg.addVertex(v3);
        alg.addVertex(v4);

        alg.addEdge(v1,v2);
        alg.addEdge(v2,v3);
        alg.addEdge(v3,v1);
        alg.addEdge(v3,v4);

        assertEquals(3, Algorithms.distance(alg, v1, v4));
    }

    @Test
    public void sd_and_dia_elaborate(){
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();
        Algorithms al = new Algorithms();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);
        Vertex<Integer> v5 = new Vertex("v5", 5);
        Vertex<Integer> v6 = new Vertex("v6", 6);
        Vertex<Integer> v7 = new Vertex("v7", 7);

        alg.addVertex(v1);
        alg.addVertex(v2);
        alg.addVertex(v3);
        alg.addVertex(v4);
        alg.addVertex(v5);
        alg.addVertex(v6);
        alg.addVertex(v7);

        alg.addEdge(v1,v2);
        alg.addEdge(v1,v3);
        alg.addEdge(v2,v4);
        alg.addEdge(v3,v4);
        alg.addEdge(v4,v5);
        alg.addEdge(v2,v5);
        alg.addEdge(v5,v6);
        alg.addEdge(v2,v6);
        alg.addEdge(v6,v7);
        alg.addEdge(v7,v1);

        assertEquals(3, Algorithms.distance(alg, v6, v2));
        assertEquals(6, Algorithms.diameter(alg));
    }

    @Test
    public void sd_and_dia_list() {
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();
        Algorithms al = new Algorithms();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);
        Vertex<Integer> v5 = new Vertex("v5", 5);
        Vertex<Integer> v6 = new Vertex("v6", 6);
        Vertex<Integer> v7 = new Vertex("v7", 7);

        alg.addVertex(v1);
        alg.addVertex(v2);
        alg.addVertex(v3);
        alg.addVertex(v4);
        alg.addVertex(v5);
        alg.addVertex(v6);
        alg.addVertex(v7);

        alg.addEdge(v1,v2);
        alg.addEdge(v1,v3);
        alg.addEdge(v2,v4);
        alg.addEdge(v3,v4);
        alg.addEdge(v4,v5);
        alg.addEdge(v2,v5);
        alg.addEdge(v5,v6);
        alg.addEdge(v2,v6);
        alg.addEdge(v6,v7);
        alg.addEdge(v7,v1);

        Set<List<Vertex<Integer>>> expectedDFS = new HashSet<>();
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v3,v1,v2,v4)); expectedDFS.add(Arrays.asList(v4)); expectedDFS.add(Arrays.asList(v2,v3,v1,v4));

        assertEquals(3, Algorithms.distance(alg, v6, v2));
        assertEquals(6, Algorithms.diameter(alg));
    }

    @Test
    public void sd_and_dia_list_more() {
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();
        Algorithms al = new Algorithms();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);
        Vertex<Integer> v5 = new Vertex("v5", 5);
        Vertex<Integer> v6 = new Vertex("v6", 6);
        Vertex<Integer> v7 = new Vertex("v7", 7);
        Vertex<Integer> v8 = new Vertex("v8", 8);

        alg.addVertex(v1);
        alg.addVertex(v2);
        alg.addVertex(v3);
        alg.addVertex(v4);
        alg.addVertex(v5);
        alg.addVertex(v6);
        alg.addVertex(v7);
        alg.addVertex(v8);

        alg.addEdge(v1,v3);
        alg.addEdge(v2,v3);
        alg.addEdge(v3,v5);
        alg.addEdge(v3,v4);
        alg.addEdge(v5,v6);
        alg.addEdge(v5,v7);
        alg.addEdge(v4,v7);
        alg.addEdge(v4,v8);

        Set<List<Vertex<Integer>>> expectedDFS = new HashSet<>();
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v3,v1,v2,v4)); expectedDFS.add(Arrays.asList(v4)); expectedDFS.add(Arrays.asList(v2,v3,v1,v4));

        assertEquals(3, Algorithms.distance(alg, v1, v8));
        assertEquals(3, Algorithms.diameter(alg));
        assertEquals(Integer.MAX_VALUE, Algorithms.distance(alg,v3,v1));
    }

    @Test
    public void inf_dia_test() {
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);

        alg.addVertex(v1);
        alg.addVertex(v2);
        alg.addVertex(v3);
        alg.addVertex(v4);


        ArrayList<Vertex<Integer>> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(v2); //expectedNeighbors.add(v3);

        assertEquals((int)Double.POSITIVE_INFINITY, Algorithms.diameter(alg),5);

    }
}
