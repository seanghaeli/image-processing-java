package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Edge;
import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 ******************************************************************************/

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    private ArrayList<Vertex<T>> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private int[][] representation = new int[1][1];
    private int matSize = 1;

    /*  AF: For each element of representation, if its column's vertex and
            it's row's vertex contains an edge between the two, is "1", else "0".
            vertices.size() <= matSize;

        RI: Square matrix of size greater than or equal to number of vertices.
            vertices has no duplicate elements or nulls, also cannot be null.
            representation is not null. Each vertex is a row and column on
            representation, where the the ith vertex corresponds to the ith
            row and ith column. Each element on representation must be mapped.*/
    @Override
    public void addVertex(Vertex<T> v) {
        this.vertices.add(v);
        if(vertices.size()>=matSize)
            repDimDouble();
    }

    @Override
    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        this.edges.add(new Edge(v1,v2));
        int v1Ind = vertices.indexOf(v1);
        int v2Ind = vertices.indexOf(v2);
        representation[v1Ind][v2Ind] = 1;
        //representation[v2Ind][v1Ind] = 1;
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
        return vertices;
    }

    //rep currently exposed!
    public int[][] getRepresentation(){
        int[][] fittedRep = new int[vertices.size()][vertices.size()];
        for(int i=0;i<vertices.size();i++){
            for(int j=0;j<vertices.size();j++){
                fittedRep[i][j] = representation[i][j];
            }
        }
        return fittedRep;
    }

    /**
     * Doubles matrix size of the representation matrix.
     */
    private void repDimDouble(){
        int newLength = representation.length*2;
       int[][] tempRep = new int[newLength][newLength];
       for(int i=0;i<representation.length+1;i++)
           for(int j=0;j<representation.length+1;j++)
               tempRep[i][j] = 0;
       for(int i=0;i<representation.length;i++)
           for(int j=0;j<representation.length;j++)
               tempRep[i][j] = representation[i][j];
       representation = tempRep;
       matSize = newLength;
    }


}
