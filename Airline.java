import java.io.*;
import java.util.*;

public class Airline 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // get cmd line arg
        File data = new File(args[0]);
        Scanner fileReader = new Scanner(data);
        
        int numOfCities = fileReader.nextInt();
        fileReader.nextLine();          // consume newline
       
        // store names of cities in array
        // indexed on vertex number
        String[] cities = new String[numOfCities];
        // make reverse mapping of city array for quick lookups
        HashMap<String, Integer> hashedCities = new HashMap<String, Integer>();
        for (int i = 0 ; i < cities.length ; i++)
        {
            cities[i] = fileReader.nextLine();
            hashedCities.put(cities[i], i);
        }
        
        
      
        // create graph based on file data
        MyGraph graph = new MyGraph(fileReader, numOfCities);

        // print cities served
        printCitiesServed(cities);
        // print direct routes
        printDirectRoutes(graph, cities);

        // find route
        // get trip info from user
        Scanner userInput = new Scanner(System.in);
        // get starting city from user 
        System.out.print("Enter Starting City: ");
        String startCity = userInput.next();
        // get destination city
        System.out.print("Enter Destination City: ");
        String destCity = userInput.next();
        // get max cost
        System.out.print("Maximum cost? ");
        int maxCost = userInput.nextInt();
        // get max hops
        System.out.print("Maximum hops? ");
        int maxHops = userInput.nextInt();

        int startVertex = hashedCities.get(startCity);
        int destVertex = hashedCities.get(destCity); 

        // use dfs to get all matching paths
        LinkedList<Path> paths = graph.findPaths(startVertex, destVertex, maxHops, maxCost);

        // ask user how they would like the paths sorted
        System.out.println("How would you like to view these paths?");
        System.out.println("1) Ordered by hops (fewest to most)");
        System.out.println("2) Ordered by cost (least expensive to most expensive)");
        System.out.println("3) Ordered by distance (shortest overall to longest overall)");
        System.out.println("or any other number to go back to the main menu");
        System.out.println("Enter your choice: ");
        // get int from user
        int sortingChoice = userInput.nextInt();
        // sort based on input
        switch (sortingChoice)
        {
            case 1: // sort by hops
            paths = Path.sortHops(paths);
            break;

            case 2: // sort by cost
            paths = Path.sortHops(paths);
            break;

            case 3: // sort by distance
            paths = Path.sortDistance(paths);
            break;

            default:
            // go to main menu
        }

       

    }





    // print a list of all the cities served
    private static void printCitiesServed(String[] cities)
    {
        System.out.println("Cities Served:");
        for (int i = 0 ; i < cities.length ; i++)
            System.out.println("\t" + cities[i]); 
        System.out.println();  
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
            for (MyEdge e : g.adj(i)) 
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
        System.out.println();
    }

   
}