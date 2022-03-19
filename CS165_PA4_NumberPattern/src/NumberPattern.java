import java.util.Scanner;

public class NumberPattern {
    // TODO: Write recursive printNumPattern() method
    public static void printNumPattern(int num1,  int num2){
        String rtn = "";
        System.out.print(printNumPatHelper(num1, num2, rtn));
    }

    public static String printNumPatHelper(int num1, int num2, String rtn){
        if(num1 <= 0) {
            rtn += "" + (num1);

        } else {
            rtn = num1 + " " + printNumPatHelper(num1 - num2, num2, rtn) + " " + num1;
        }
        return rtn;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int num1;
        int num2;


        num1 = scnr.nextInt();
        num2 = scnr.nextInt();
        printNumPattern(12, 3);
    }
} 