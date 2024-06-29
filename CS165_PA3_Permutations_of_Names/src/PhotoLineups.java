
import java.util.Scanner;
import java.util.ArrayList;

public class PhotoLineups {

    // TODO: Write method to create and output all permutations of the list of names.
    public static void allPermutations(ArrayList<String> permList, ArrayList<String> nameList) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        permuteHelper(list, permList, nameList);
        for(ArrayList<String> q : list)
        {
            for(String r : q) {
                System.out.print(r + " ");
            }
            System.out.println();
        }

    }

    public static void permuteHelper(ArrayList<ArrayList<String>> list, ArrayList<String> permList, ArrayList<String> nameList) {
        if(permList.size() == nameList.size())
        {
            list.add(new ArrayList<>(permList));
        }
        else {
            for(int i = 0; i < nameList.size(); i++)
            {
                if(permList.contains(nameList.get(i)))
                {
                    continue;
                }
                permList.add(nameList.get(i));
                permuteHelper(list, permList, nameList);

                permList.remove(permList.size() - 1);
            }

        }

    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> permList = new ArrayList<String>();
        String name = scnr.next();

        // TODO: Read in a list of names; stop when -1 is read. Then call recursive method.
        while(!name.equals("-1"))
        {
            nameList.add(name);
            name = scnr.next();
        }
        allPermutations(permList, nameList);
    }
}