import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Airline 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // get cmd line arg
        File data = new File(args[0]);
        Scanner reader = new Scanner(data);
        
        int numOfCities = reader.nextInt();
        reader.nextLine();          // consume newline
       
        // store names of cities in array
        // indexed on vertex number
        String[] cities = new String[numOfCities];
        for (int i = 0 ; i < cities.length ; i++)
        {
            cities[i] = reader.nextLine();
        }
        
      
        // create graph based on file data
        MyGraph graph = new MyGraph(reader, numOfCities);

        // print cities served
        printCitiesServed(cities);
        // print direct routes
        printDirectRoutes(graph, cities);

        
        
    }





    // print a list of all the cities served
    private static void printCitiesServed(String[] cities)
    {
        System.out.println("Cities Served:");
        for (int i = 0 ; i < cities.length ; i++)
            System.out.println("\t" + cities[i]);   
    }



    // print all edges in the graph
    private static void printDirectRoutes(MyGraph g, String[] cities)
    {
        System.out.println("Direct Routes:");
        System.out.println("From\t\tTo\t\tCost\tDistance");
        // loop through vertices
        for (int i = 0 ; i < g.V() ; i++)
        {
            // get adjacency list for each vertex
            for (MyGraph.MyEdge e : g.adj(i)) 
            {
                // for each edge, diplay info
                int v = e.from();
                System.out.print(cities[v]);
                // adding extra tab if city is Erie, Altoona, or Reading (this is just to neatly format output)
                if (v == 1 || v == 2 || v == 7)
                    System.out.print("\t");

                System.out.print("\t" + cities[e.to()]);
                if (e.to() == 1 || e.to() == 2 || e.to() == 7)
                    System.out.print("\t");

                System.out.print("\t" + e.cost());
                System.out.println("\t" + e.distance());
            }
        }
    }
}
