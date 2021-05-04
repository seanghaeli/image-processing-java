package ca.ubc.ece.cpen221.ip.mp;

import static org.junit.Assert.*;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.Algorithms;
import org.junit.Test;

import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;

import java.util.*;

public class Task2Tests {

    //breadth-first search tests

    //bfs with list representation for graph
    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    @Test
    public void bfs_and_dfs_four_node_list() {
        AdjacencyListGraph<Integer> alg = new AdjacencyListGraph();
        Algorithms al = new Algorithms();

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

        Set<List<Vertex<Integer>>> expectedBFS = new HashSet<>();
        expectedBFS.add(Arrays.asList(v1,v2,v3,v4)); expectedBFS.add(Arrays.asList(v3,v1,v4,v2)); expectedBFS.add(Arrays.asList(v4)); expectedBFS.add(Arrays.asList(v2,v3,v1,v4));

        Set<List<Vertex<Integer>>> expectedDFS = new HashSet<>();
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v3,v1,v2,v4)); expectedDFS.add(Arrays.asList(v4)); expectedDFS.add(Arrays.asList(v2,v3,v1,v4));

        assertEquals(expectedBFS, al.breadthFirstSearch(alg));
        assertEquals(expectedDFS, al.depthFirstSearch(alg));
    }

    //bfs and dfs with matrix representation for graph
    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    @Test
    public void bfs_and_dfs_four_node_mat() {
        AdjacencyMatrixGraph<Integer> amg = new AdjacencyMatrixGraph();
        Algorithms al = new Algorithms();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);

        amg.addVertex(v1);
        amg.addVertex(v2);
        amg.addVertex(v3);
        amg.addVertex(v4);

        amg.addEdge(v1,v2);
        amg.addEdge(v2,v3);
        amg.addEdge(v3,v1);
        amg.addEdge(v3,v4);

        Set<List<Vertex<Integer>>> expectedBFS = new HashSet<>();
        expectedBFS.add(Arrays.asList(v1,v2,v3,v4)); expectedBFS.add(Arrays.asList(v3,v1,v4,v2)); expectedBFS.add(Arrays.asList(v4)); expectedBFS.add(Arrays.asList(v2,v3,v1,v4));

        Set<List<Vertex<Integer>>> expectedDFS = new HashSet<>();
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v3,v1,v2,v4)); expectedDFS.add(Arrays.asList(v4)); expectedDFS.add(Arrays.asList(v2,v3,v1,v4));


        assertEquals(expectedDFS, al.depthFirstSearch(amg));
        assertEquals(expectedBFS, al.breadthFirstSearch(amg));
    }

    //depth-first search tests

    ////dfs with list representation for graph
    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    @Test
    public void bfs_and_dfs_elaborate_list() {
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
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4,v5,v6,v7)); expectedDFS.add(Arrays.asList(v2,v4,v5,v6,v7,v1,v3)); expectedDFS.add(Arrays.asList(v3,v4,v5,v6,v7,v1,v2)); expectedDFS.add(Arrays.asList(v4,v5,v6,v7,v1,v2,v3)); expectedDFS.add(Arrays.asList(v5,v6,v7,v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v6,v7,v1,v2,v3,v4,v5)); expectedDFS.add(Arrays.asList(v7,v1,v2,v3,v4,v5,v6));

        assertEquals(Arrays.asList(v1), Algorithms.commonUpstreamVertices(alg,v2,v3));
        assertEquals(Arrays.asList(v5), Algorithms.commonDownstreamVertices(alg,v2,v4));
        assertEquals(expectedDFS, al.breadthFirstSearch(alg));
//        assertEquals(expectedDFS, al.depthFirstSearch(alg));
    }

    ////dfs with matrix representation for graph
    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    @Test
    public void dfs_elaborate() {
        AdjacencyMatrixGraph<Integer> amg = new AdjacencyMatrixGraph();
        Algorithms al = new Algorithms();

        Vertex<Integer> v1 = new Vertex("v1", 1);
        Vertex<Integer> v2 = new Vertex("v2", 2);
        Vertex<Integer> v3 = new Vertex("v3", 3);
        Vertex<Integer> v4 = new Vertex("v4", 4);

        amg.addVertex(v1);
        amg.addVertex(v2);
        amg.addVertex(v3);
        amg.addVertex(v4);

        amg.addEdge(v1,v2);
        amg.addEdge(v2,v3);
        amg.addEdge(v3,v1);
        amg.addEdge(v3,v4);

        Set<List<Vertex<Integer>>> expectedDFS = new HashSet<>();
        expectedDFS.add(Arrays.asList(v1,v2,v3,v4)); expectedDFS.add(Arrays.asList(v3,v1,v2,v4)); expectedDFS.add(Arrays.asList(v4)); expectedDFS.add(Arrays.asList(v2,v3,v1,v4));


        assertEquals(expectedDFS, al.depthFirstSearch(amg));
    }
}
