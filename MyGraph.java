import java.util.Scanner;

public class MyGraph 
{
    private final int V;
    private int E;
    private Bag<MyEdge>[] adj;
    
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

   public class MyEdge
   {
        int v;          // one vertex in edge
        int w;          // other vertex in edge
        int distance;
        double cost;

        /**
        * Create an edge between v and w with given weight.
        */
        public MyEdge(int v, int w, int distance, double cost) {
            this.v = v;
            this.w = w;
            this.distance = distance;
            this.cost = cost;
        }

        /**
        * Return the distance of this edge.
        */
        public double distance() {
            return distance;
        }


        /**
        * Return the cost of this edge.
        */
        public double cost() {
            return cost;
        }

        /**
        * Return vertex edge is coming from
        */
        public int from() {
            return v;
        }

        /**
        * Return vertex edge is going to
        */
        public int to() {
           return w;
        }

        /**
        * Compare edges by distance.
        */
        public int compareDistance(MyEdge that) {
            if      (this.distance() < that.distance()) return -1;
            else if (this.distance() > that.distance()) return +1;
            else                                        return  0;
        }

         /**
        * Compare edges by distance.
        */
        public int compareCost(MyEdge that) {
            if      (this.cost() < that.cost()) return -1;
            else if (this.cost() > that.cost()) return +1;
            else                                return  0;
        }

        /**
        * Return a string representation of this edge.
        */
        public String toString() {
            String s = v + " --> " + w + " cost: " + cost + " distance: " + distance;
            return s;
        }

   }
}
