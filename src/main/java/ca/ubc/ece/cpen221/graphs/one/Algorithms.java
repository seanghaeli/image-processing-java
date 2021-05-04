package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.util.*;

public class Algorithms {

    /**
     * *********************** Algorithms ****************************
     *
     * Please see the README for this machine problem for a more detailed
     * specification of the behavior of each method that one should
     * implement.
     */

    /**
     * The distance between two vertices is the length of the shortest
     * path between the two vertices. The distance between a vertex and
     * itself is 0. If no path exists from s to t then your method should
     * take appropriate action.
     *
     * @param graph for graph being represented
     * @param a starting node on graph
     * @param b ending node on graph
     * @return integer for the minimum number of edges that would have
     * to be crossed to get from a to b.
     */
    public static <T> int distance(Graph<T> graph, Vertex<T> a, Vertex<T> b) {
        int[] dist = new int[graph.getVertices().size()];
        List<Vertex<T>> pred = graph.getVertices();

        if(connectionChecker(graph,a,b, dist)==false){
            return Integer.MAX_VALUE;
        }

        // Note that this method can be invoked as follows:
        //      Algorithms.<String>shortestDistance(g, a, b)
        // when the graph contains vertices of type Vertex<String>.
        // The compiler can also perform type inference so that we can simply use:
        //      Algorithms.shortestDistance(g, a, b)
        //return shortestDistanceHelper(graph, visited, a, b);
        return dist[graph.getVertices().indexOf(b)];
    }


    /**
     * Checks if there is a connection between two vertices
     *
     * @param graph for graph rep.
     * @param src for starting vertex
     * @param dest for destination vertex
     * @param dist for integer array of distance of vertices
     *             from source array to be passed back to
     *             caller
     * @modifies dist array
     * @return true if there is connection between source
     * and destination vertices, otherwise false.
     */
    public static <T> boolean connectionChecker(Graph<T> graph, Vertex<T> src, Vertex<T> dest, int[] dist){
        ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
        List<Vertex<T>> visited = new ArrayList<>();
        queue.add(src);


        for(int i=0;i<graph.getVertices().size();i++) {
            dist[i] = -1;
        }
        int test = graph.getVertices().indexOf(src);
        dist[graph.getVertices().indexOf(src)]=0;

        while(!queue.isEmpty()){
            List<Vertex<T>> neighborsToAdd = (List<Vertex<T>>) getUnvisitedNonqueuedNeighbors(graph.getNeighbors(queue.getFirst()), visited, queue);
            queue.addAll(neighborsToAdd);
            visited.add(queue.getFirst());
            for(Vertex eligibleNeighbor: neighborsToAdd){
                if(dist[graph.getVertices().indexOf(eligibleNeighbor)]==-1) {
                    dist[graph.getVertices().indexOf(eligibleNeighbor)] = dist[graph.getVertices().indexOf(queue.getFirst())]+1;
//                    pred.set(pred.indexOf(eligibleNeighbor), queue.getFirst());
                    if(eligibleNeighbor.equals(dest))
                        return true;
                }
            }
            queue.removeFirst();


        }
        return false;
    }

    /**
     * Gets the distance array of all other vertices from
     * a given vertex. Useful as a helper function.
     *
     * @param graph for graph rep.
     * @param a vertex to get distance of all others from
     * @return integer array of distances to vertex from
     * vertex a, indexes correspond to position of vertex
     * on graph.getVertices() order.
     */
    private static <T> int[] distanceArray(Graph<T> graph, Vertex<T> a) {
        int[] dist = new int[graph.getVertices().size()];

        //init array with -1, signifying that
        //distances have not been determined yet.
        for(int i=0;i<dist.length;i++)
            dist[i]=-1;

        //distance of start vertex is zero.
        dist[graph.getVertices().indexOf(a)] = 0;

        //reusing BFS implementation and modifying
        ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
        List<Vertex<T>> visited = new ArrayList<>();
        queue.add(a);
        while(!queue.isEmpty()){
            //queue.addAll(graph.getNeighbors(queue.getFirst()));
            List<Vertex<T>> neighborsToAdd = (List<Vertex<T>>) getUnvisitedNonqueuedNeighbors(graph.getNeighbors(queue.getFirst()), visited, queue);
            queue.addAll(neighborsToAdd);
            visited.add(queue.getFirst());
            for(Vertex eligibleNeighbor: neighborsToAdd){
                if(dist[graph.getVertices().indexOf(eligibleNeighbor)]==-1) {
                    dist[graph.getVertices().indexOf(eligibleNeighbor)] = dist[graph.getVertices().indexOf(queue.getFirst())]+1;
                }
            }
            queue.removeFirst();


        }

        return dist;
    }

    /**
     * Perform a complete depth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a DFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param graph for graph being represented
     * @return set of lists of order of visited vertices in
     * depth first search. Size of set corresponds to amount
     * of vertices
     */
    public static <T> Set<List<Vertex<T>>> depthFirstSearch(Graph<T> graph) {
        Set<List<Vertex<T>>> dfsAll = new HashSet<>();
        for(Vertex vertex: graph.getVertices()){
            List<Vertex<T>> visited = new ArrayList<>();
//            visited.add(vertex);
            dfsAll.add(depthFirstSearchRecursiveHelper(graph, visited, vertex));
        }
        return dfsAll; // this should be changed

    }

    /**
     * Helper function for depth first search.
     * Recursively complete depth first search by
     * moving to possible unvisited neighbors of
     * "start" vertex, and then calling function
     * again with the new start as the vertex we
     * moved to.
     *
     * @param graph for graph being represented
     * @param visited previously seen vertices
     * @param start current initial vertex
     * @param <T> any object
     * @return
     */
    private static <T> List<Vertex<T>> depthFirstSearchRecursiveHelper(Graph<T> graph, List<Vertex<T>> visited, Vertex start){
        if(visited.contains(start))
            return visited;
        visited.add(start);

        List<Vertex<T>> sortedNeighbors = graph.getNeighbors(start);
        Collections.sort(sortedNeighbors, new LexographicalComparator());

        for(Object neighbor: sortedNeighbors){
            if((neighbor instanceof Vertex)){
                depthFirstSearchRecursiveHelper(graph, visited, (Vertex)neighbor);
            }
        }

        return visited;
    }

    /**
     * Perform a complete breadth first search of the given
     * graph. Start with the search at each vertex of the
     * graph and create a list of the vertices visited.
     * Return a set where each element of the set is a list
     * of elements seen by starting a BFS at a specific
     * vertex of the graph (the number of elements in the
     * returned set should correspond to the number of graph
     * vertices).
     *
     * @param
     * @return set of lists of order of visited vertices in
     * breadth first search. Size of set corresponds to amount
     * of vertices
     */
    public static <T> Set<List<Vertex<T>>> breadthFirstSearch(Graph<T> graph) {
        Set<List<Vertex<T>>> bfsAll = new HashSet<>();
        for(Vertex vertex: graph.getVertices()){
            ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
            List<Vertex<T>> visited = new ArrayList<>();
            queue.add(vertex);
            while(!queue.isEmpty()){
                //queue.addAll(graph.getNeighbors(queue.getFirst()));
                queue.addAll(getUnvisitedNonqueuedNeighbors(graph.getNeighbors(queue.getFirst()), visited, queue));
                visited.add(queue.getFirst());
                queue.removeFirst();
            }
            bfsAll.add(visited);
        }

        return bfsAll;
    }

    /**
     * Helper method for breadth first search. Returns
     * unvisited neighbors that are also not already
     * in the queue to be visited.
     *
     * @param neighbors list of neighbors
     * @param visited list of already visited vertices
     * @param queued list of queued vertices
     * @param <T> any object
     * @return
     */
    private static <T> Collection<? extends Vertex<T>> getUnvisitedNonqueuedNeighbors(List neighbors, List<Vertex<T>> visited, ArrayDeque<Vertex<T>> queued) {
        List<Vertex<T>> unvisitedNeighbors = new ArrayList<>();
        neighbors = sortVertices(neighbors);
        for(Object vertex: neighbors)
            if((vertex instanceof Vertex))
                if(!(visited.contains(vertex) || queued.contains(vertex)))
                    unvisitedNeighbors.add((Vertex)neighbors.get(neighbors.indexOf(vertex)));

        return unvisitedNeighbors;
    }

    /** Lexographical comparator of vertices based
     * on vertex label, sorts it in order.
    * @param \list of vertices
    * */
    private static <T> List sortVertices(List<Vertex<T>> vertices){
        Collections.sort(vertices, new LexographicalComparator());
        return vertices;
    }


    /**
     * Maximum finite distance from any pair of node
     * on graph. If no nodes are connected, returns
     * infinite.
     */
    public static <T> int diameter(Graph<T> graph) {
        int max=-1;

        for(Vertex vertex: graph.getVertices()) {
            int[] dist = distanceArray(graph, vertex);
                for (int i=0; i<dist.length;i++)
                    if(dist[i]>max)
                        max=dist[i];
        }
        if(max==0)
            return (int)Double.POSITIVE_INFINITY;
        return max;
    }

    /**
     * Given a graph G and two vertices a and b
     * in G, your implementation should return a
     * list of all vertices u such that there is
     * an edge from u to a and an edge from u to
     * b. If there are no such vertices exist,
     * returns an empty list.
     * @param graph for graph being represented
     * @param a one vertex
     * @param b the other vertex
     * @return list of common upstream vertices
     */
    public static <T> List<Vertex<T>>  commonUpstreamVertices(Graph<T> graph, Vertex<T> a, Vertex<T> b){
        List<Vertex<T>> cUV = new ArrayList<>();
        for(Vertex vertex: graph.getVertices())
            if(graph.edgeExists(vertex, a) && graph.edgeExists(vertex, b))
                cUV.add(vertex);

        return cUV;
    }

    /**
     * Given a graph G and two vertices a and b
     * in G, your implementation should return a
     * list of all vertices v such that there is
     * an edge from a to v and an edge from b to
     * v.
     * @param graph for graph being represented
     * @param a one vertex
     * @param b the other vertex
     * @return list of common downstream vertices
     */
    public static <T> List<Vertex<T>>  commonDownstreamVertices(Graph<T> graph, Vertex<T> a, Vertex<T> b){
        List<Vertex<T>> dUV = new ArrayList<>();
        for(Vertex vertex: graph.getVertices())
            if(graph.edgeExists(a, vertex) && graph.edgeExists(b, vertex))
                dUV.add(vertex);

        return dUV;
    }

}

class LexographicalComparator implements Comparator<Vertex>{
    /** Lexographical comparator specifically for vertex labels
    * @param a is the first vertex to compare with
    * @param b is the second vertex
    * @return the comparison between the two vertices (-1, 0, 1)
    * */
    public int compare(Vertex a, Vertex b){
        return a.getLabel().compareToIgnoreCase(b.getLabel());
    }
}
