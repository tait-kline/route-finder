// Author: Tait Kline
// This class is my attempt at an object oriented approach to comparable paths
// This class contains three comparator classes that can be used to sort paths based on hops, distance, and cost

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
        this.totalDistance = p.totalDistance;

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

    public String toString(String[] names)
    {
        StringBuilder sb = new StringBuilder(new String("Hops: " + totalHops + " Cost: " + totalCost + 
                                        " Distance: " + totalDistance + "\n"));
        
        // get string representaion of the edges in the path
        for (MyEdge e : path) 
        {
            sb.append("\t" + names[e.from()] + " -> ");
            sb.append(names[e.to()] + " ");
            sb.append("cost: " + e.cost + " distance: " + e.distance + "\n");

        }

        return sb.toString();
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