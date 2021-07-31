// Author: Tait Kline
// Note that I used the author's graph code as a starting point for this class
// This graph is directional and contains the findPaths() methos that is used in the main class
// to find all paths in the graph that meet the argument criteria

import java.util.LinkedList;
import java.util.Scanner;

public class MyGraph 
{
    private final int V;
    private int E;
    private Bag<MyEdge>[] adj;
    private LinkedList<Path> paths;
    private boolean[] marked;    
    
   /**
     * Create an empty edge-weighted graph with V vertices.
     */
    public MyGraph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<MyEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) 
            adj[v] = new Bag<MyEdge>();
    }

   

   /**
     * Create a graph from input file.
     * Cities argument is the number of cities to be used as vertices.
     * Scanner must only contain the actual edge data, not the string names of cities
     */
    public MyGraph(Scanner reader, int cities) {
        this(cities);
        while(reader.hasNextLine())
        {
            int v = reader.nextInt();
            int w = reader.nextInt();
            int distance = reader.nextInt();
            double cost = reader.nextDouble();
            MyEdge e = new MyEdge(v,w,distance,cost);
            addEdge(e);
            // this graph is directed, so we need to add an edge in the other direction
            // because flights go both ways in this scenorio
            int temp = v;
            v = w;
            w = temp;
            e = new MyEdge(v,w,distance,cost);
            addEdge(e);
        }
        
    }

    public LinkedList<Path> findPaths(int start, int destination, int maxHops, int maxCost)
    {
        Path p = new Path();
        paths = new LinkedList<>();
        marked = new boolean[V];
        recFindPaths(start, destination, maxHops, maxCost, p);
        return paths;
    }

    private void recFindPaths(int currVertex, int destination, int maxHops, int maxCost, Path currPath)
    {
       
        // base case, found valid path
        if (currVertex == destination)
        {
            // store copy of current path in path list
            Path foundPath = new Path(currPath);
            paths.add(foundPath);
            return;
        }
        
        marked[currVertex] = true;
        // loop through curr vertex adj list
        for (MyEdge e : adj(currVertex))
        {
            // if next vertex has not been visited
            if (!marked[e.to()])
                // if next vertex will not exceed maximum hops and cost
                if(((currPath.getCost() + e.cost) <= maxCost) && ((currPath.getHops() + 1) <= maxHops))
                {
                    // add edge to path
                    currPath.addEdge(e);
                    // recurse with next vertex
                    recFindPaths(e.to(), destination, maxHops, maxCost, currPath);
                    currPath.removeEdge();
                }
   
        }
        marked[currVertex] = false;
       
    }

   /**
     * Return the number of vertices in this graph.
     */
    public int V() {
        return V;
    }

   /**
     * Return the number of edges in this graph.
     */
    public int E() {
        return E;
    }


   /**
     * Add the edge e to this graph.
     */
    public void addEdge(MyEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;
    }


   /**
     * Return the edges incident to vertex v as an Iterable.
     * To iterate over the edges incident to vertex v, use foreach notation:
     * <tt>for (Edge e : graph.adj(v))</tt>.
     */
    public Iterable<MyEdge> adj(int v) {
        return adj[v];
    }

   /**
     * Return all edges in this graph as an Iterable.
     * To iterate over the edges, use foreach notation:
     * <tt>for (Edge e : graph.edges())</tt>.
     */
    public Iterable<MyEdge> edges() {
        Bag<MyEdge> list = new Bag<MyEdge>();
        for (int v = 0; v < V; v++) {
            for (MyEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }



   /**
     * Return a string representation of this graph.
     */
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (MyEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

   

   
}