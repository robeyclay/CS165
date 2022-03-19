import java.util.Scanner;

public class WhatOrder{
    // TODO: Define a generic method called checkOrder() that
    //       takes in four variables of generic type as arguments.
    //       The return type of the method is integer
    public static <E extends Comparable<E>>  int checkOrder(E i1, E i2, E i3, E i4) {
        if(i1.compareTo(i2) > 0) {
            if(i2.compareTo(i3) > 0) {
                if(i3.compareTo(i4) > 0) {
                    return 1;
                }
                else return 0;
            }
            else return 0;
        } else if (i4.compareTo(i3) > 0) {
            if (i3.compareTo(i2) > 0) {
                if (i2.compareTo(i1) > 0) {
                    return -1;
                }
                else return 0;
            }
            else return 0;
        }
        else return 0;
    }

    // Check the order of the input: return -1 for ascending,
    // 0 for neither, 1 for descending



    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Check order of four strings
        System.out.println("Order: " + checkOrder(scnr.next(), scnr.next(), scnr.next(), scnr.next()));

        // Check order of four doubles
        System.out.println("Order: " + checkOrder(scnr.nextDouble(), scnr.nextDouble(), scnr.nextDouble(), scnr.nextDouble()));
    }
}
