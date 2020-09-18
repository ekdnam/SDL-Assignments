package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class newServer {

    public newServer(){
        Company com = new Company(true);
    }


    public static void main(String[] arg) {
        Company com = new Company(true);

        Integer ch;
        String choice, y, send, recieve;
        try {
            ServerSocket socketConnection = new ServerSocket(11111);
            System.out.println("\nServer listening on port: 11111");

            Socket pipe = socketConnection.accept();
            System.out.println("Socket connection from " + pipe);
            ObjectInputStream serverInputStream = new ObjectInputStream(
                    pipe.getInputStream()
            );
            ObjectOutputStream serverOutputStream = new ObjectOutputStream(
                    pipe.getOutputStream()
            );

            //         System.out.println("choice="+choice);
            String name = "";
            int i = 0;

            while (true) {
                choice = (String) serverInputStream.readObject();
                ch = Integer.parseInt(choice);
                System.out.println("Main value recd: " + ch);
                switch (ch) {
                    case 1: {
                        while (true) {
                            choice = (String) serverInputStream.readObject();
                            System.out.println("\n\nValue recd: " + choice);
                            ch = Integer.parseInt(choice);
                            switch (ch) {
                                case 1: {
                                    System.out.println("user recd");
                                    User u = (User) serverInputStream.readObject();
                                    com.database.put(u.username, u);
                                    send = "Account creation successful!";
                                    System.out.println(send + " Username: " + u.username);
                                    serverOutputStream.writeObject(send);
                                    System.out.println("Users are: ");
                                    com.showUsers();
                                    break;
                                }
                                case 2: {
//                                    recieve = (String) serverInputStream.readObject();
//                                    int id = Integer.parseInt(recieve);
//                                    Employee E = (Employee) hm.get(id);
//                                    serverOutputStream.writeObject(E);
                                    System.out.println("\nUser details branch.");
                                    String username = (String) serverInputStream.readObject();
                                    String password = (String) serverInputStream.readObject();
                                    System.out.println("Values recd \nUsrname: " + username + "\nPassword: " + password);
                                    if (com.database.containsKey(username)) {
                                        User u = new User(com.database.get(username));
                                        if (password.equals(u.password)) {
                                            System.out.println("Username and password verified.");
                                            serverOutputStream.writeObject(u);
                                        } else {
                                            System.out.println("Username verified. Pasword wrong.");
                                            User a = null;
                                            serverOutputStream.writeObject(a);
                                        }
                                    } else {
                                        System.out.println("Username and password both are wrong.");
                                        User a = null;
                                        serverOutputStream.writeObject(a);
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.println("\nPlacing orders");
                                    String username = (String) serverInputStream.readObject();
                                    String password = (String) serverInputStream.readObject();
                                    System.out.println("Values recd \nUsrname: " + username + "\nPassword: " + password);
                                    if (com.database.containsKey(username)) {
                                        User u = new User(com.database.get(username));
                                        if (password.equals(u.password)) {
                                            System.out.println("Username and password verified.");
                                            serverOutputStream.writeObject(u);
                                            System.out.println("Generating copy of ProductLine...");
//                                            ProductLine line = new ProductLine(com.line);
                                            System.out.println("Sending products");
//                                            line.Products.putAll(com.line.Products);
                                            serverOutputStream.writeObject(com);
                                            System.out.println("Products sent");
                                            String msg = (String) serverInputStream.readObject();
                                            if (msg.equals("product exists")) {
                                                System.out.println("Product exists.\nReceiving order");
                                                Order o = (Order) serverInputStream.readObject();
                                                com.pq.add(o);
                                                System.out.println("Order from " + username + " added to queue");
//                                                System.out.println("The current orders are: ");
                                                com.showQueue();
                                            }
                                        } else {
                                            System.out.println("Username verified. Pasword wrong.");
                                            User a = null;
                                            serverOutputStream.writeObject(a);
                                        }
                                    } else {
                                        System.out.println("Username and password both are wrong.");
                                        User a = null;
                                        serverOutputStream.writeObject(a);
                                    }
                                    break;
                                }
                            }
                            y = (String) serverInputStream.readObject();
                            if (!y.equals("y")) break;
                        }

                        break;
                    }
                    case 2: {
                        String username = (String) serverInputStream.readObject();
                        String password = (String) serverInputStream.readObject();
                        boolean resp = false;
                        if (username.equals(com.companyName) && password.equals(com.password)) {
                            resp = true;
                            serverOutputStream.writeObject(resp);
                        } else {
                            resp = false;
                            serverOutputStream.writeObject(resp);
                        }
                        if (resp) {
                            System.out.println("Successful signing in for admin.");
//                            serverOutputStream.writeObject(com);
                            while (true) {
                                String chx = (String) serverInputStream.readObject();
                                switch (chx){
                                    case "1":
                                        serverOutputStream.writeObject(com);
                                        System.out.println("Admin is accessing the users");
                                        break;
                                    case "2":
                                        serverOutputStream.writeObject(com);
                                        System.out.println("Admin is accessing the delivery queue");
                                        break;
                                    default:
                                        break;
                                }
                                y = (String) serverInputStream.readObject();
                                if (!y.equals("y")) break;
                            }
                        } else {
                            System.out.println("Error in signing in for admin.");
                        }
                        break;

                    }
                    case 3:
                        System.out.println("\nClient is requesting for chatbox. Initializing chatbox\n");
                        String msgin = "", msgout = "";
                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            msgin = (String) serverInputStream.readObject();
                            if (!msgin.equals("./end")) {
                                System.out.printf("Client: " + msgin);
                                System.out.printf("\nServer: ");
                                msgout = sc.nextLine();
                                serverOutputStream.writeObject(msgout);
                            } else break;
                        }
                        System.out.println("Exiting chatbox");
                        break;
                }
                y = (String) serverInputStream.readObject();
                if (!y.equals("y")) break;
            }
            serverInputStream.close();
            serverOutputStream.close();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
            System.out.println("Stack trace: " + e.getStackTrace());
        }
    }
}