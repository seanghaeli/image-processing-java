package ca.ubc.ece.cpen221.graphs.core;

import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.AdjacencyListGraph;

import java.util.Arrays;

        /*
         * dependent on Vertex
         *
         * datatype that represents directed edges between two vertices.
         *
         * */

public class Edge {
    public final Vertex v1;
    public final Vertex v2;

    /*
        AF: represents a directed edge between two vertices.
        RI: edge v1 is base vertex (from vertex), edge v2 is tip vertex (to vertex).
     */


    /**
     * Create a new edge with two vertices as ends.
     *
     * @param _v1 base vertex
     * @param _v2 head vertex
     */
    //representation exposed currently
    public Edge(Vertex _v1, Vertex _v2) {
        this.v1 = _v1;
        this.v2 = _v2;
    }

    /**
     * Check equality of edges. This method overrides equals( ) in Object.
     *
     * @return true if this edge is equal to the obj otherwise return false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) o;
        return v1.equals(other.v1) && v2.equals(other.v2);
    }

//    public boolean undirectedEquals(Object o) {
//        if (!(o instanceof Edge)) {
//            return false;
//        }
//        Edge other = (Edge) o;
//        return (v1.equals(other.v1) && v2.equals(other.v2)) || (v1.equals(other.v2) && v2.equals(other.v1));
//    }
}
