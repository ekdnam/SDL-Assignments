package com.company;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Scanner;

/*
 * class User
 *
 * is the template to represent an User
 * has the parameters:
 * String username, String name, int age, int priority, String password, BitSet specialValues
 * */
public class User implements Serializable {
//    Integer uid;
    String username;
    String name;
    int age;
    int priority;
    String password;

    // bitset[0] - yes for special offers email
    // bitset[1] - premium membership
    // bitset[2] = fast delivery
    BitSet specialValues;

    public User(User u) {
//        this.uid = u.uid;
        this.username = u.username;
        this.password = u.password;
        this.age = u.age;
        this.priority = u.priority;
        this.name = u.name;
        this.specialValues = u.specialValues;
    }

    public User() {
        Scanner sc = new Scanner(System.in);
        this.username = new String();
        this.username = "";
        this.name = new String();
        this.name = "";
        this.age = -1;
        this.specialValues = new BitSet(6);
        priority = 0;
    }

    public User(String username, String name, int age) {
        this.username = new String();
        this.username = username;
        this.name = new String();
        this.name = name;
        this.age = age;
        this.specialValues = new BitSet(6);
        priority = 0;
    }

    public User(String username, String name, String password, int age) {
        this.username = new String();
        this.username = username;
        this.name = new String();
        this.name = name;
        this.age = age;
        this.specialValues = new BitSet(6);
        this.password = password;
        priority = 0;
    }

    public void showUserDetails() {
        System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "Password", "Age");
        System.out.printf("%-22s%-22s%-22s%-22s\n", this.username, this.name, this.password, this.age);
    }

    public void getUserDetails() {
        System.out.println("Creating new user...\nEnter details as asked...");
        Scanner sc = new Scanner(System.in);
        System.out.printf("Username: ");
        String username = sc.nextLine();
        System.out.printf("Password: ");
        String password = sc.nextLine();
        this.password = password;
        this.username = username;
        System.out.printf("Name: ");
        String name = sc.nextLine();
        this.name = name;
        System.out.printf("Age: ");
        int age = sc.nextInt();
        this.age = age;
//        User u = new User(username, name, age);
        System.out.printf("Do you want to receive offers(1/0): ");
        int val = sc.nextInt();
        if (val == 1) {
            this.specialValues.set(0);
            this.priority += 1;
        }
        System.out.printf("Do you want premium membership(1/0): ");
        val = sc.nextInt();
        if (val == 1) {
            this.specialValues.set(1);
            this.priority += 1;
        }
        System.out.printf("Do you want to faster delivery(1/0): ");
        val = sc.nextInt();
        if (val == 1) {
            this.specialValues.set(2);
            this.priority += 1;
        }
        System.out.println();
    }

    public User(boolean value, String username, String password, String name, int age){
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }
}
