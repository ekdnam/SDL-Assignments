package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class newClient {

    public static void main(String[] arg) {
        String y, send, recieve;
        int ch;
        try {
            TreeMap<String, User> db = new TreeMap<String, User>();
            PriorityQueue<Order> pqcomp = new PriorityQueue<Order>(100, new TheComparator());
            Socket socketConnection = new Socket("127.0.0.1", 11111);
            Scanner sc = new Scanner(System.in);

            ObjectOutputStream clientOutputStream = new
                    ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new
                    ObjectInputStream(socketConnection.getInputStream());
            PrintStream ptr = new PrintStream(socketConnection.getOutputStream());
            System.out.println("Welcome to Wayne Enterprises!");
            while (true) {
                System.out.printf("\n%-22s%-22s\n","Customer","1");
                System.out.printf("%-22s%-22s\n","Admin","2");
                System.out.printf("%-22s%-22s\n","Chatbox","3");
                System.out.printf("Choice: ");
                ch = sc.nextInt();
                send = "" + ch;
                clientOutputStream.writeObject(send);
                int id1 = 0;
                switch (ch) {
                    case 1: {
                        while (true) {
                            System.out.printf("\n%-22s%-22s","New customer", "1");
                            System.out.printf("\n%-22s%-22s", "See your details", "2");
                            System.out.printf("\n%-22s%-22s\n", "Place an order", "3");
//                            System.out.println("See our products: 4");
                            System.out.printf("Choice: ");
                            ch = sc.nextInt();
                            send = "" + ch;
                            clientOutputStream.writeObject(send);
                            System.out.println();
                            switch (ch) {
                                case 1: {
                                    // user creation
                                    User u = new User();
                                    u.getUserDetails();
                                    // send user to server
                                    clientOutputStream.writeObject(u);
                                    recieve = (String) clientInputStream.readObject();

                                    System.out.println();
                                    System.out.println(recieve);
                                    System.out.println();
                                    break;
                                }
                                case 2: {
                                    System.out.println();
                                    System.out.println("\nEnter username and password of user to see details");
                                    System.out.println();
                                    String username, password;
                                    username = new String();
                                    password = new String();
                                    System.out.printf("Username: ");
                                    sc.nextLine();
                                    username = sc.nextLine();
                                    System.out.printf("Password: ");
                                    password = sc.nextLine();
                                    clientOutputStream.writeObject(username);
                                    clientOutputStream.writeObject(password);
                                    User u = (User) clientInputStream.readObject();
                                    if (u == null) {
                                        System.out.println("The username / password was incorrect.");
                                    } else {
                                        System.out.println("Your details are: ");
                                        u.showUserDetails();
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.println();
                                    System.out.println("Enter username and password to sign in");
                                    System.out.println();
                                    String username, password;
                                    username = new String();
                                    password = new String();
                                    System.out.printf("Username: ");
                                    sc.nextLine();
                                    username = sc.nextLine();
                                    System.out.printf("Password: ");
                                    password = sc.nextLine();
                                    clientOutputStream.writeObject(username);
                                    clientOutputStream.writeObject(password);
                                    User u = (User) clientInputStream.readObject();
                                    if (u == null) {
                                        System.out.println("The username / password was incorrect.");
                                    } else {
                                        System.out.println("Your details are: ");
                                        u.showUserDetails();
                                        //ProductLine p = new ProductLine();
                                        Company com = (Company) clientInputStream.readObject();
                                        //p.Products.putAll(x.Products);
                                        //p.nProducts = x.nProducts;
                                        System.out.println("The products are: ");
                                        com.line.showProducts();
                                        System.out.println("Which product do you want?");
                                        System.out.printf("Product name: ");
//                                        sc.nextLine();
                                        String productName = sc.nextLine();
                                        Product x = com.line.search(productName);
                                        if (x == null) {
                                            clientOutputStream.writeObject("product does not exist in database");
                                            System.out.println("Product does not exist in database. Check for spelling errors");
                                        } else {
                                            clientOutputStream.writeObject("product exists");
                                            System.out.println("Order created\n");
                                            Order o = new Order(u, x);
                                            clientOutputStream.writeObject(o);
                                            pqcomp.add(o);
                                        }
                                    }
                                    break;
                                }
                                default:
                                    System.out.println("Choice does not match with our parameters"); break;
                            }
                            System.out.println("Do you want to continue for customer (y/n)");
                            y = sc.next();
                            send = y;
                            clientOutputStream.writeObject(send);
                            if (!"y".equals(y)) break;
                        }
                        break;
                    }

                    case 2: {
                        System.out.println("Assessing admin credentials");
                        sc.nextLine();
                        System.out.printf("Username: ");
                        String username = sc.nextLine();
                        System.out.printf("Password: ");
                        String password = sc.nextLine();
                        clientOutputStream.writeObject(username);
                        clientOutputStream.writeObject(password);
                        boolean resp = (boolean) clientInputStream.readObject();
                        if (resp == false) {
                            System.out.println("Admin creds were wrong. Exiting admin menu");
                        } else {
//                            Company com = (Company) clientInputStream.readObject();
                            while (true) {
                                System.out.printf("\n%-22s%-22s", "Users in the database" ,"1");
                                System.out.printf("\n%-22s%-22s","Orders in the queue","2");
                                System.out.printf("\nChoice: ");
                                ch = sc.nextInt();
                                send = "" + ch;
                                clientOutputStream.writeObject(send);

                                switch (ch) {
                                    case 1: {
                                        Company com = (Company) clientInputStream.readObject();
                                        com.showUsers();
                                        break;
                                    }

                                    case 2: {
                                        Company com = (Company) clientInputStream.readObject();
//                                        com.showQueue();
                                        if (!pqcomp.isEmpty()) {
                                            System.out.println("The current queue is: ");
                                            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "ProductName", "Cost");
                                            for (Order o : pqcomp) {
                                                System.out.printf("%-22s%-22s%-22s%-22s\n", o.u.username, o.u.name, o.p.productName, o.p.cost);
                                            }
                                        } else {
                                            System.out.println("The delivery queue is empty...");
                                        }
                                        break;
                                    }
                                    default:
                                        System.out.println("Choice entered does not match with our parameters."); break;
                                }
                                System.out.println("Do you want to continue for admin (y/n)");
                                y = sc.next();
                                send = y;
                                clientOutputStream.writeObject(send);
                                if (!"y".equals(y)) break;
                            }
                        }
                        break;
                    }
                    case 3: {
                        sc.nextLine();
                        String msgin = "", msgout = "";
                        System.out.println("Initializing chat application. Wait for some time.");
                        System.out.println("Initialized. To exit enter dot slash end.");
                        System.out.printf("Client: ");
                        msgout = sc.nextLine();
                        while (true) {
                            if (!msgout.equals("./end")) {

                                clientOutputStream.writeObject(msgout);
                                msgin = (String) clientInputStream.readObject();
                                System.out.printf("\nServer: " + msgin);
                                System.out.printf("\nClient: ");
                                msgout = sc.nextLine();
                            } else {
                                clientOutputStream.writeObject(msgout);
                                break;
                            }
                        }
                        break;
                    }
//                    default:
//                        System.out.println("Choice entered does not match with our parameters."); break;
                }
                System.out.println("Do you want to continue for admin/customer (y/n)");
                y = sc.next();
                send = y;
                clientOutputStream.writeObject(send);
                if (!"y".equals(y)) break;
            }
            clientOutputStream.close();
            clientInputStream.close();

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
            System.out.println("Stack trace: " + e.getStackTrace());
        }
    }
//
//    public void showQueue() {
//        if (!pqcomp.isEmpty()) {
//            System.out.println("The current queue is: ");
//            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "ProductName", "Cost");
//            for (Order o : pqcomp) {
//                System.out.printf("%-22s%-22s%-22s%-22s\n", o.u.username, o.u.name, o.p.productName, o.p.cost);
//            }
//        } else {
//            System.out.println("The delivery queue is empty...");
//        }
//    }
}