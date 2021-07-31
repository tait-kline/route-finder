// Author: Tait Kline
// This class contatins the main method for my project
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
        Scanner userInput = new Scanner(System.in);


        // main menu:
        boolean mainMenu = true;       // control mainMenu loop
        while(mainMenu)
        {
            System.out.println("Please choose from the options below:");
            System.out.println("1) List cities served and direct routes");
            System.out.println("2) Find a route");
            System.out.println("3) Quit program");
            int mainMenuInput = userInput.nextInt();
            switch (mainMenuInput)
            {
                case 1: // list cities and direct routes
                // print cities served
                printCitiesServed(cities);
                // print direct routes
                printDirectRoutes(graph, cities);
                break;

                case 2: // find routes
                // get starting city from user 
                System.out.print("Enter Starting City: ");
                String startCity = userInput.next();
                // validate user input
                while (!hashedCities.containsKey(startCity))
                {
                    System.out.println("This city is not one of the cities served. Please check your spelling.");
                    printCitiesServed(cities);
                    System.out.print("Enter Starting City: ");
                    startCity = userInput.next();
                }
                
                // get destination city
                System.out.print("Enter Destination City: ");
                String destCity = userInput.next();
                // validate user input
                while (!hashedCities.containsKey(destCity))
                {
                    System.out.println("This city is not one of the cities served. Please check your spelling.");
                    printCitiesServed(cities);
                    System.out.print("Enter Destination City: ");
                    destCity = userInput.next();
                }
                // get max cost
                System.out.print("Maximum cost? ");
                int maxCost = userInput.nextInt();
                // validate input
                while(maxCost <= 0)
                {
                    System.out.println("Maximum cost must be greater than 0.");
                    System.out.print("Maximum cost? ");
                    maxCost = userInput.nextInt();
                }
                // get max hops
                System.out.print("Maximum hops? ");
                int maxHops = userInput.nextInt();
                // validate input
                while(maxHops <= 0)
                {
                    System.out.println("Maximum hops must be greater than 0.");
                    System.out.print("Maximum hops? ");
                    maxHops = userInput.nextInt();
                }
                System.out.println();

                int startVertex = hashedCities.get(startCity);
                int destVertex = hashedCities.get(destCity); 

                // use dfs to get all matching paths
                LinkedList<Path> paths = graph.findPaths(startVertex, destVertex, maxHops, maxCost);
                // if no paths are found, go back to main menu options
                if (paths.isEmpty())
                    {
                        System.out.println("Sorry but there are no paths from " + startCity + " to " +
                                            destCity + " that meet your criteria.");
                        break;
                    }
                System.out.println("There are " + paths.size() + " paths from " + startCity
                            + " to " + destCity + " with maximum cost " + maxCost + 
                            " and at most " + maxHops + " hops.\n");

                boolean sorted = true;          // used to control sorting switch statement
                while (sorted)
                {
                    // ask user how they would like the paths sorted
                    System.out.println("How would you like to view these paths?");
                    System.out.println("1) Ordered by hops (fewest to most)");
                    System.out.println("2) Ordered by cost (least expensive to most expensive)");
                    System.out.println("3) Ordered by distance (shortest overall to longest overall)");
                    System.out.println("or any other number to go back to the main menu");
                    System.out.print("Enter your choice: ");
        
                    // get int from user
                    int sortingChoice = userInput.nextInt();
                    System.out.print("\n");
                    // sort based on input (uses comparator class in path class)
                    switch (sortingChoice)
                    {
                        case 1: // sort by hops
                        paths = Path.sortHops(paths);
                        System.out.println("Paths from " + startCity + " to " + destCity + " sorted by hops:");
                        // display sorted paths
                        for (Path p : paths) 
                        {
                            System.out.println(p.toString(cities));    
                        }
                        break;

                        case 2: // sort by cost
                        paths = Path.sortCost(paths);
                        System.out.println("Paths from " + startCity + " to " + destCity + " sorted by cost:");
                        // display sorted paths
                        for (Path p : paths) 
                        {
                            System.out.println(p.toString(cities));    
                        }
                        break;

                        case 3: // sort by distance
                        paths = Path.sortDistance(paths);
                        System.out.println("Paths from " + startCity + " to " + destCity + " sorted by distance:");
                        // display sorted paths
                        for (Path p : paths) 
                        {
                            System.out.println(p.toString(cities));    
                        }
                        break;

                        default: // go to main menu
                        sorted = false;
                    }
                }

                break;

                case 3: // quit main menu
                    mainMenu = false;
                    break;

                default:
                    System.out.println("Input is Invalid!");

            }
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
        //System.out.println("From\t\tTo\t\tCost\tDistance");
        System.out.printf("%-22s%-22s%-22s%-22s\n","From","To","Cost","Distance");
        // loop through vertices
        for (int i = 0 ; i < g.V() ; i++)
        {
            // get adjacency list for each vertex
            for (MyEdge e : g.adj(i)) 
            {
                // for each edge, diplay info
                System.out.printf("%-22s%-22s%-22.2f%-22d\n",cities[e.from()],cities[e.to()],e.cost(),e.distance());
            }
        }
        System.out.println();
    }

   
}