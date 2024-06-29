// GraphImplementation.java - supplied code for graph assignment

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GraphImplementation extends GraphAbstract {

    // Main entry point
    public static void main(String[] args) {

        // Instantiate code
        GraphImplementation impl = new GraphImplementation();
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        String startCity = input.nextLine();
        // Read distances chart
        System.out.println("Reading Chart: " + fileName);
        impl.readGraph(fileName);
        System.out.println();

        // Print depth first search
        System.out.println("Depth First Search:");
        impl.depthFirst(startCity);
        System.out.println();

        System.out.println("Breadth First Search:");
        impl.breadthFirst(startCity);
        System.out.println();

    }

    // Reads mileage chart from CSV file
    public void readGraph(String filename) {
        try {
            // YOUR CODE HERE
            ArrayList<String> data = new ArrayList<>(readFile(filename));
            String[] temp = data.get(0).split(",");

            for(String p: temp) {
                GraphNode newNode = new GraphNode(p);
                cities.add(newNode);
            }

            for(int i = 1; i < data.size(); i++) {
                String[] newCity = data.get(i).split(",");
                for(int k = 1; k < newCity.length; k++) {
                    if (!newCity[k].equals("")) {
                        GraphEdge toEdge = new GraphEdge(i - 1, k - 1, Integer.parseInt(newCity[k]));
                        GraphEdge fromEdge = new GraphEdge(k - 1, i - 1, Integer.parseInt(newCity[k]));
                        mileages.add(toEdge);
                        GraphNode fromCity = cities.get(i);
                        GraphNode toCity = cities.get(k);
                        fromCity.edges.add(toEdge);
                        toCity.edges.add(fromEdge);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error at readGraph: " + e);
        }
        cities.remove(0);

 /*       System.out.println("cities:");
        for(GraphNode p : cities) {
            System.out.print("*" + p.name + " ");
        }
       System.out.println();
        System.out.println("mileages: ");
        for(GraphEdge p: mileages) {
            System.out.print(p.mileage + " ");
        }
        System.out.println();


        System.out.println("edges: ");
        int counter = 0;
        for(GraphNode p : cities) {
            //System.out.println("Size: " + p.edges.size());
            for(GraphEdge c : p.edges) {
                System.out.println(p.name + ": " + c.toIndex);
            }
        }
*/

    }


    public void depthFirst(String startCity) {
        // YOUR CODE HERE
        GraphNode indexCity = new GraphNode(startCity);
        int startIndex = 0;
        for(int i = 0; i < cities.size(); i++) {
            if(cities.get(i).equals(indexCity)) {
                startIndex = i;
                break;
            }
        }
        //System.out.println("startIndex: " + startIndex);
        ArrayList<Integer> visited = new ArrayList<>();
        depthFirst(startIndex, visited);
    }

    // Recursive helper method
    public void depthFirst(int index, ArrayList<Integer> visited) {
        // YOUR CODE HERE
        GraphNode curr = cities.get(index);
        System.out.println("Visited " + curr.name);
        visited.add(index);
        edgeSort(curr.edges);
        for(GraphEdge edge : curr.edges) {

            if(!visited.contains(edge.toIndex)) {
                depthFirst(edge.toIndex, visited);
            }
        }
    }

    public void edgeSort(ArrayList<GraphEdge> edges){
        for(int i = 0; i < edges.size(); i++) {
            for(int k = edges.size() - 1; k > i; k--) {
                GraphEdge temp;
                if(edges.get(i).compareTo(edges.get(k)) >= 0) {
                    temp = edges.get(i);
                    edges.set(i, edges.get(k));
                    edges.set(k, temp);
                }
            }
        }
    }

    public void breadthFirst(String startCity) {
        // YOUR CODE HERE
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        GraphNode curr = new GraphNode(startCity);

        queue.add(cities.indexOf(curr));
        visited.add(cities.indexOf(curr));

        while(!queue.isEmpty()) {
            GraphNode topQueue = cities.get(queue.remove(0));
            edgeSort(topQueue.edges);
            System.out.println("Visited " + topQueue.name);
            for(GraphEdge edges : topQueue.edges) {
                if(!visited.contains(edges.toIndex)) {
                    queue.add(edges.toIndex);
                    visited.add(edges.toIndex);
                }
            }
            visited.add(cities.indexOf(topQueue));
        }

    }


    // Helper functions

    /**
     * Reads the contents of file to {@code ArrayList}
     * @param filename the file to read from
     * @return an ArrayList of the contents
     */
    static ArrayList<String> readFile(String filename) {
        ArrayList<String> contents = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty())
                    contents.add(line);
            }
        } catch (IOException e) {
            System.err.println("Cannot read chart: " + filename);
        }
        return contents;
    }


}
