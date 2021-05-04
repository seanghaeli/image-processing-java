package ca.ubc.ece.cpen221.ip.mp;

import static org.junit.Assert.*;

import ca.ubc.ece.cpen221.graphs.core.Edge;
import ca.ubc.ece.cpen221.graphs.core.Graph;
import org.junit.Test;

import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.ArrayList;
import java.util.Arrays;

public class Task1Tests {

    // List tests

    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    // Expected list representation: https://gyazo.com/8e7b7cdcb23b8ba99a33d8d093a6585a
    @Test
    public void alg_four_node() {
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

        ArrayList<Vertex<Integer>> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(v2); //expectedNeighbors.add(v3);

        ArrayList<ArrayList<Vertex<Integer>>> expectedListRepresentation = new ArrayList<>();
        expectedListRepresentation.add(new ArrayList<>(Arrays.asList(v2))); expectedListRepresentation.add(new ArrayList<>(Arrays.asList(v3))); expectedListRepresentation.add(new ArrayList<>(Arrays.asList(v1, v4))); expectedListRepresentation.add(new ArrayList<>(Arrays.asList()));

        assertEquals(v1, alg.getVertices());
        assertTrue(alg.edgeExists(v1,v2));
        assertEquals(expectedNeighbors, alg.getNeighbors(v1));
//        assertEquals(expectedListRepresentation, alg.getRepresentation());

    }

    //Matrix tests

    // Visualization of graph being represented: https://gyazo.com/347193d3f9cd000c1ea6435b1ef5f401
    // Expected matrix representation: https://gyazo.com/42ac8a3ab8070542eacb1a67ef9fcbbc
    @Test
    public void amg_four_node() {
        AdjacencyMatrixGraph<Integer> amg = new AdjacencyMatrixGraph();

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

        ArrayList<Vertex<Integer>> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(v2); //expectedNeighbors.add(v3);
        int[][] expectedMatrixRepresentation = { {0,1,0,0},
                                                 {0,0,1,0},
                                                 {1,0,0,1},
                                                 {0,0,0,0}
                                                            };
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2,v1);
        v1.setLabel("v1");
        v1.getContent();
        v1.setContent(1);
        v1.toString();
        v1.equals(e1);

        assertTrue(amg.edgeExists(v1,v2));
        assertEquals(expectedNeighbors, amg.getNeighbors(v1));
        assertFalse(e1.equals(e2));
        assertFalse(e1.equals(v1));
        assertEquals(expectedMatrixRepresentation, amg.getRepresentation());

    }
}
