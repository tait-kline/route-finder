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
     public int distance() {
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
         String s = v + " --> " + w;
         return s;
     }

}