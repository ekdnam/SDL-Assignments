package com.company;
import java.util.BitSet;
import java.util.Enumeration;

public class User {
    String username;
    String name;
    // String address;
    int age;
    int priority;
    String password;

    // bitset[0] - yes for special offers email
    // bitset[1] - premium membership
    // bitset[2] = fast delivery
    BitSet specialValues;

    public User(String username, String name, int age){
        //this.address = new String();
        //this.address = address;
        this.username = new String();
        this.username = username;

        this.name = new String();
        this.name = name;

        this.age = age;

        this.specialValues = new BitSet(6);

        priority = 0;
    }


    public User(String username, String name, String password, int age){
        //this.address = new String();
        //this.address = address;
        this.username = new String();
        this.username = username;

        this.name = new String();
        this.name = name;

        this.age = age;

        this.specialValues = new BitSet(6);

        this.password = password;

        priority = 0;
    }
}
