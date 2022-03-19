import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PlantArrayListExample {

    // TODO: Define a printArrayList method that prints an ArrayList of plant (or flower) objects
    public static void printArrayList(ArrayList<Plant> myGarden){

        for(int i = 0; i < myGarden.size(); i++)
        {
            myGarden.get(i).printInfo();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String input;
        // TODO: Declare an ArrayList called myGarden that can hold object of type plant
        ArrayList<Plant> myGarden = new ArrayList<>();
        // TODO: Declare variables - plantName, plantCost, colorOfFlowers, isAnnual
        String plantName;
        String plantCost;
        String colorOfFlowers;
        Boolean isAnnual;

        input = scnr.next();
        while(!input.equals("-1")){
            // TODO: Check if input is a plant or flower
            //       Store as a plant object or flower object
            //       Add to the ArrayList myGarden
            if(input.equalsIgnoreCase("plant")){
                Plant plantToGarden = new Plant();
                plantName = scnr.next();
                plantCost = scnr.next();
                plantToGarden.setPlantCost(plantCost);
                plantToGarden.setPlantName(plantName);
                myGarden.add(plantToGarden);
            } else {
                Flower flowToGarden = new Flower();
                plantName = scnr.next();
                plantCost = scnr.next();
                isAnnual = Boolean.parseBoolean(scnr.next());
                colorOfFlowers = scnr.next();
                flowToGarden.setPlantName(plantName);
                flowToGarden.setPlantCost(plantCost);
                flowToGarden.setPlantType(isAnnual);
                flowToGarden.setColorOfFlowers(colorOfFlowers);
                myGarden.add(flowToGarden);
            }

            input = scnr.next();
        }

        // TODO: Call the method printArrayList to print myGarden
        printArrayList(myGarden);

    }
}
