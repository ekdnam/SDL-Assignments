package com.company;

import java.util.*;

public class Company {
    String companyName;
    TreeMap<String, User> database;
    ProductLine line;
    // Enumeration enumQueue;
    Vector v;
    PriorityQueue<Order> pq;
    int users;
    String password;

    public Company(){
        Enumeration e;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter company name: ");
        this.companyName = sc.nextLine();

        System.out.println("Enter password: ");
        this.password = sc.nextLine();

        this.line = new ProductLine();
        this.database = new TreeMap<String, User>();
        this.users = 0;

        v = new Vector();
        v.add("Special offers");
        v.add("Premium membership");
        v.add("Fast delivery");

        e = v.elements();

        System.out.println("We currently have the following offers! ");
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }

        this.pq = new PriorityQueue<Order>(100, new TheComparator());
    }

    public void createOffers(){
        System.out.println("The new offer: ");
        Scanner sc = new Scanner(System.in);

        String offer = sc.nextLine();
        this.v.add(offer);
    }
    public void showOffers(){
        Enumeration e = v.elements();
//        System.out.println("We have the following offers! ");
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
    }
    public void createLine(){
        line.getProducts();
    }

    public void getOrder() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = sc.nextLine();

        if (database.containsKey(username)) {
            User u = database.get(username);

            line.showProducts();
            System.out.println("Which product do you want?");
            String prod = sc.nextLine();
            if (line.Products.containsKey(prod)) {
                Product p = line.Products.get(prod);
                Order o = new Order(u, p);
                pq.add(o);
                System.out.println("Your order is successfully added to the queue! ");
                p.qty -= 1;
            } else {
                System.out.println("Please enter correct product name");
            }

        }
        else{
            System.out.println("Username not present in database...");
        }
    }
    public void createUser(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Age: ");
        Integer age = sc.nextInt();
        User u = new User(username, name, age);

        System.out.println("Do you want to receive offers(1/0): ");
        int val = sc.nextInt();
        if(val == 1){
            u.specialValues.set(0);
            u.priority += 1;
        }

        System.out.println("Do you want premium membership(1/0): ");
        val = sc.nextInt();
        if(val == 1){
            u.specialValues.set(1);
            u.priority += 1;
        }

        System.out.println("Do you want to faster delivery(1/0): ");
        val = sc.nextInt();
        if(val == 1){
            u.specialValues.set(2);
            u.priority += 1;
        }

        database.put(username, u);

        this.users += 1;
    }

    public void showQueue(){
        if(!pq.isEmpty()) {
            System.out.println("The current queue is: ");
            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "ProductName", "Cost");
//        System.out.println("Username    Name    ProductName     Cost");
            for (Order o : pq) {
                System.out.printf("%-22s%-22s%-22s%-22s\n", o.u.username, o.u.name, o.p.productName, o.p.cost);
            }
        }
        else{
            System.out.println("The delivery queue is empty...");
        }
    }

    public void showUsers(){
        if(database.isEmpty()){
            System.out.println("Users not added to database");
        }
        else {
            System.out.print("Current users of the system are: \n");
            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "Age", "Priority");
            for (Map.Entry<String, User> entry : database.entrySet()) {
//                System.out.printf("%-22s%-22s%-22s%\n", entry.getKey(), entry.getValue().name, entry.getValue().age);
                System.out.printf("%-22s%-22s%-22s%-22s\n", entry.getKey(), entry.getValue().name, entry.getValue().age, entry.getValue().priority);
//            System.out.println(entry.getKey() + "   " + entry.getValue().name + "   "+ entry.getValue().age);
            }
        }
    }
}

class Order{
    User u;
    Product p;
    int priority;
    Order(User u, Product p){
        this.u = u;
        this.p = p;
        this.priority = u.priority;
    }
}

// comparator
class TheComparator implements Comparator<Order>{
    @Override
    public int compare(Order a, Order b){
        if(a.priority > b.priority){
            return -1;
        }
        if(a.priority < b.priority){
            return 1;
        }
        return 0;
    }
}