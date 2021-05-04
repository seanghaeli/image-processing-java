**CPEN 221 / Fall 2020 / MiniProject 2**

The Graph ADT and Virtual Worlds
===

This mini-project has two parts. Part 2 can benefit from your implementation of Part 1.

## Part 1: Unweighted Directed Graphs

In this part of the mini-project, you will implement a `Graph` interface using two different graph representations. You will then develop several algorithms that use the `Graph` interface that might be used in a social network.

Your goals for this machine problem are to:
+ Understand and apply the concept of encapsulation;
+ Understand interfaces;
+ Understand what graphs are and how they can be represented;
+ Implement some basic graph algorithms.

**Preliminary reference**: Wikipedia entry on Graphs https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)

### Task 1: Basic Graph Implementations
First, write two classes that implement the `ca.ubc.ece.cpen221.graphs.core.Graph` interface, which represents a _directed_ graph.
+ **Adjacency List**: Inside the package `ca.ubc.ece.cpen221.graphs.one`, implement the `AdjacencyListGraph` class. Your implementation must internally represent the graph as an _adjacency list_. If you are not familiar with the adjacency list representation of graphs, see the [Wikipedia page on the adjacency list representation](https://en.wikipedia.org/wiki/Adjacency_list) as a reference.
+ **Adjacency Matrix**: Next, implement the `AdjacencyMatrixGraph` class in the `ca.ubc.ece.cpen221.graphs.one` package. Your implementation must internally represent the graph as an adjacency matrix. If you are not familiar with the adjacency matrix representation of graphs, see the [Wikipedia page on the adjacency matrix representation](https://en.wikipedia.org/wiki/Adjacency_matrix) as a reference.

This task uses Java Generics. Each `Vertex` may hold content of a specified type. For example, a graph that where each vertex containts a `String` as content would be declared using the type `Graph<String>` and a vertex in such a graph would be of type `Vertex<String>`. (This is similar to how `List`s are implemented in the standard Java library.)

### Task 2: Graph Algorithms 1
For this part of the assignment, you will implement algorithms that might be used for social network analysis using your graph implementations.

Your algorithms must use only the methods provided in the interface, and can not use any features specific to the implementation of `Graph` being used. Your algorithms must work correctly on any correct implementation of a `Graph`, including your `AdjacencyMatrixGraph` and `AdjacencyListGraph` implementations.

_All your algorithms must work on directed graphs (digraphs)._

+ **Breadth first search (BFS)**: Implement the [breadth first search](https://en.wikipedia.org/wiki/Breadth-first_search) algorithm to traverse a graph.
+ **Depth first search (DFS)**: Implement the [depth first search](https://en.wikipedia.org/wiki/Depth-first_search) algorithm to traverse a graph.

For both BFS and DFS above, you should use traverse the entire graph. You should return a set of lists (`Set<List<Vertex>>`). Each list in the set is a connected component of the graph. The vertices in each list are in the order they were visited by the traversal routines. More specifically, start a BFS or DFS traversal at every possible vertex and each such traversal will produce a list. The set of all these lists is what you want to return. Furthermore, when deciding which vertex to visit next, you should select the vertex with the lexicographically smaller label. This approach ensures that there is exactly one list corresponding to a BFS or DFS starting at a given vertex.

### Task 3: Graph Algorithms 2

+ **Common upstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _u_ such that there is an edge from _u_ to _a_ and an edge from _u_ to _b_. If there are no such vertices then your implementation should return an empty list.
+ **Common downstream vertices**: Given a graph _G_ and two vertices _a_ and _b_ in _G_, your implementation should return a list of all vertices _v_ such that there is an edge from _a_ to _v_ and an edge from _b_ to _v_.  
+ **Distance**: Implement a method to find the distance between two vertices in an unweighted graph. In an unweighted graph _G_, given two vertices _s_ and _t_, the distance between the two vertices is the minimum number of edges that would have to be traversed to get to _t_ from _s_. In other words, the distance between two vertices is the length of the shortest path between the two vertices. The distance between a vertex and itself is 0. If no path exists from _s_ to _t_ then your method should take appropriate action.
+ **Graph diameter**: The diameter of a graph is the maximum distance among the distances between all pairs of vertices in the graph. If it is not possible to get to vertex _t_ from vertex _s_ then the distance between those two vertices, and consequently the graph diameter, is _infinity_. We will, however, adopt a more relaxed definition where we will specify the diameter as being the maximum finite distance among pairs of vertices except in the case when all distances are infinite (when we will treat the diameter as infinite). Implement a method to determine the diameter of a graph.

### Task 4: Analyzing Social Networks

> As long as your code runs in a _reasonable_ amount of time, and returns the correct values, you do not need to worry about the [time complexity](https://en.wikipedia.org/wiki/Time_complexity) of your algorithms. _However_, there may be some extra credit for particularly efficient implementations.

An anonymized dataset from Twitter is provided in the file `datasets/twitter.txt`. The file contains many rows, with each row containing an entry of the form `a -> b` to indicate that `b` follows `a`.

We would like to answer the following questions from this dataset:

+ **Common influencers**: Given users `a` and `b`, who are the common users that both `a` and `b` follow?
+ **Count retweets**: Suppose user `a` tweets a message, what is the minimum number of retweets needed before `a`'s tweet appears in `b`'s feed? (The assumption here is that a tweet will appear in `b`'s feed if the tweet originated from one of the users that `b` follows.)

You should implement suitable methods for such analysis, including a `public static void main(String[ ] args)` method in a class named `TwitterAnalysis`. The `main` method in `TwitterAnalysis` should handle command-line arguments.
