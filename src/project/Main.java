package project;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ScaleList list = new ScaleList(7);
        list.generateScales();
        System.out.println("All scales, no restrictions: ");
        System.out.println("Total number: " + list.getScales().size() + "\n");
        list.printScales();
        
        System.out.println("\n=======================================================\n");
        ArrayList<Scale> allRejectedScales = new ArrayList<Scale>();
        for (int i = 0; i < list.getConststraints().size(); i ++) {
            ArrayList<Scale> rejectedScales = list.enforceRestrictions(i+1);
            if (i == 0) {
                System.out.println("Scales restricted by " + (i+1) + " constraint:");
            } else {
                System.out.println("Scales restricted by " + (i+1) + " constraints:");
            }
            System.out.println("Total number: " + rejectedScales.size() + "\n");
            list.printScales(rejectedScales);
            System.out.println();
            allRejectedScales.addAll(rejectedScales);
        }
        ArrayList<Scale> finalList = list.removeRejectedScales(allRejectedScales);
        System.out.println("=======================================================\n");
        System.out.println("Total scales produced from Inclusion-Exclusion:");
        System.out.println("Total number: " +  + finalList.size() + "\n");
        list.printScales(finalList);
        
        ArrayList<Scale> decendingScales = list.getDecendingScales(finalList);
        System.out.println("\n=======================================================\n");
        System.out.println("Total scales with their ascending and decending components:\n");
        System.out.println("\tAscending:\t\t\tDecending:\t\t\tAscending:\t\t\tDecending:");
        for (int i = 0; i < finalList.size(); i ++) {
            if ((i % 2) == 0) {
                System.out.print(finalList.get(i) + "\t" + decendingScales.get(i) + "\t");
            } else {
                System.out.println(finalList.get(i) + "\t" + decendingScales.get(i));
            }
            
        }
                
    }

}
