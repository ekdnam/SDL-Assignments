package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello! This is Assignment 1 for SDL");
        System.out.println("Data Structures, Java Collections, Java Interfaces used: ");
        System.out.printf("%-22s%-22s%-22s\n", "TreeMap", "Enumeration", "Comparator");
        System.out.printf("%-22s%-22s%-22s\n", "Vectors", "Priority Queue", "HashMap");
        System.out.printf("%-33s\n", "BitSet");
        System.out.println("Creating company...");
        Company com = new Company();
        com.createLine();
        choose(com);
    }

    public static void options(){
        System.out.println("\n1: Create User");
        System.out.println("2: Show Users");
        System.out.println("3: Create Order");
        System.out.println("4: Show current delivery queue");
        System.out.println("5: Show offers");
        System.out.println("6: Add offer");
        System.out.println("7: Exit");
        System.out.printf("\n");
    }
    public static void choose(Company com){
        options();
        System.out.println("Your choice? ");
        Scanner sc = new Scanner(System.in);
        Integer aus = sc.nextInt();
        while(aus != 7) {
            switch (aus) {
                case 1:
                    com.createUser();
                    break;
                case 2:
                    com.showUsers();
                    break;
                case 3:
                    com.getOrder();
                    break;
                case 4:
                    com.showQueue();
                    break;
                case 5:
                    System.out.println("We have the following offers! ");
                    com.showOffers();
                    break;
                case 6:
                    com.createOffers();
                    System.out.println("The current offers are: ");
                    com.showOffers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Enter valid option...");
            }
            options();
            System.out.println("Your choice? ");
            aus = sc.nextInt();
        }
    }
}
