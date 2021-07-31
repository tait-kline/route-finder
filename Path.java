import java.util.*;

public class Path 
{
    private Deque<MyEdge> path;
    private int totalHops;
    private int totalDistance;
    private double totalCost;

    public Path()
    {
        path = new ArrayDeque<MyEdge>();
        totalHops = 0;
        totalCost = 0;
        totalDistance = 0;
    }

    // copy constructor
    public Path(Path p)
    {
        this.path = new ArrayDeque<MyEdge>();
        for (MyEdge e : p.path) 
        {
            this.path.addLast(e);
        }
            
        this.totalHops = p.totalHops;
        this.totalCost = p.totalCost;

    }

    // add argument edge to end of path
    public void addEdge(MyEdge edge)
    {
        path.addLast(edge);
        totalHops++;
        totalCost = totalCost + edge.cost();
        totalDistance = totalDistance + edge.distance();
    }
    // remove edge from end of path
    public void removeEdge()
    {
        MyEdge removedEdge = path.removeLast();
        totalHops--;
        totalCost = totalCost - removedEdge.cost;
        totalDistance = totalDistance - removedEdge.distance;
    }

    public int getHops()
    {
        return totalHops;
    }

    public double getCost()
    {
        return totalCost;
    }

    public int getDistance()
    {
        return totalDistance;
    }

    public static LinkedList<Path> sortHops(LinkedList<Path> list)
    {
        Path sorter = new Path();
        Collections.sort(list, sorter. new SortByHops());
        return list;
    }

    public static LinkedList<Path> sortDistance(LinkedList<Path> list)
    {
        Path sorter = new Path();
        Collections.sort(list, sorter. new SortByDistance());
        return list;
    }

    public static LinkedList<Path> sortCost(LinkedList<Path> list)
    {
        Path sorter = new Path();
        Collections.sort(list, sorter. new SortByCost());
        return list;
    }


    class SortByHops implements Comparator<Path>
    {

        public int compare(Path a, Path b)
        {
            return a.totalHops - b.totalHops;
        }
    
    }

    class SortByDistance implements Comparator<Path>
    {

        public int compare(Path a, Path b)
        {
            return a.totalDistance - b.totalDistance;
        }
    
    }

    class SortByCost implements Comparator<Path>
    {

        public int compare(Path a, Path b)
        {
            return (int)(a.totalCost - b.totalCost);
        }
    
    }
}