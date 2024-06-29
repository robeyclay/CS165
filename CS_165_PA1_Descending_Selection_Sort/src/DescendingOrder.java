import java.util.Scanner;

public class DescendingOrder {
    // TODO: Write a void method selectionSortDescendTrace() that takes
    //       an integer array and the number of elements in the array as arguments,
    //       and sorts the array into descending order.
    public static void selectionSortDescendTrace(int [] numbers, int numElements)
    {

        int maxIndex;
        int temp;
        for (int i = 0; i < numElements - 1; i++)
        {
            maxIndex = i;
            for (int k = i + 1; k < numElements; k++)
            {
                if (numbers[k] > numbers[maxIndex])
                {
                    maxIndex = k;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[maxIndex];
            numbers[maxIndex] = temp;
            for (int q = 0; q < numElements; q++)
            {
                System.out.print(numbers[q] + " " );
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        int input, i = 0;
        int numElements = 0;
        int [] numbers = new int[10];

        while(scnr.hasNext())
        {
            input = scnr.nextInt();
            if (input != -1)
            {
                numElements++;
                numbers[numElements] = input;
            }
        }
        selectionSortDescendTrace(numbers, numElements);



        // TODO: Read in a list of up to 10 positive integers; stop when
        //       -1 is read. Then call selectionSortDescendTrace() method.

    }
}

