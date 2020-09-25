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

                                    int reconc = (int) clientInputStream.readObject();

                                    if(reconc == -3){
                                        System.out.println("Username does not exist in database");
                                        break;
                                    }
                                    else if(reconc == 0){
                                        System.out.println("Password doesn't match");
                                    }
                                    else if(reconc == -5){
                                        System.out.println("Unhandled exceptions occurred, exiting from this loop");
                                        break;
                                    }
                                    else if(reconc == 1){
                                        System.out.println("User authorized. Showing user details");
                                        String arr[] = new String[5];
                                        arr = (String[]) clientInputStream.readObject();
                                        System.out.printf("\n%-22s%-22s%-22s%-22s%-22s", "UID", "Username", "Password", "Name", "Age");
                                        System.out.printf("\n%-22s%-22s%-22s%-22s%-22s", arr[0], arr[1], arr[2], arr[3], arr[4]);

                                        break;
                                    }
                                    break;
                                }
                                case 3: {

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

                                    int reconc = (int) clientInputStream.readObject();

                                    if(reconc == -3){
                                        System.out.println("Username does not exist in database");
                                        break;
                                    }
                                    else if(reconc == 0){
                                        System.out.println("Password doesn't match");
                                    }
                                    else if(reconc == -5){
                                        System.out.println("Unhandled exceptions occurred, exiting from this loop");
                                        break;
                                    }
                                    else if(reconc == 1){
                                        System.out.println("User authorized. Showing user details");

                                        List<String> pnames = new ArrayList<String>();
                                        List<String> pids = new ArrayList<String>();
                                        List<String> pcosts = new ArrayList<String>();
                                        List<String> pqtys = new ArrayList<String>();

                                        pids = (List<String>) clientInputStream.readObject();
                                        pnames = (List<String>) clientInputStream.readObject();
                                        pcosts = (List<String>) clientInputStream.readObject();
                                        pqtys = (List<String>) clientInputStream.readObject();

                                        Iterator pidit = pids.iterator();
                                        Iterator pnamesit = pnames.iterator();
                                        Iterator pcostsit = pcosts.iterator();
                                        Iterator pqtysit = pqtys.iterator();


                                        System.out.printf("%-22s%-22s%-22s%-22s", "PID", "Name", "Cost", "Qty");
                                        while(pidit.hasNext() && pnamesit.hasNext() && pcostsit.hasNext() && pqtysit.hasNext()){
                                            System.out.printf("\n%-22s%-22s%-22s%-22s", pidit.next(), pnamesit.next(), pcostsit.next(), pqtysit.next());
                                        }

                                        System.out.println("\nWhich product do you want? (name)");
                                        String pname = sc.next();
                                        if(pnames.contains(pname)){
                                            System.out.println("Adding to queue. Thanks for shopping with us!");
                                            clientOutputStream.writeObject(username);
                                            clientOutputStream.writeObject(pname);

                                            int resp = (int) clientInputStream.readObject();

                                            if(resp==1){
                                                System.out.println("Order successfully added!");
                                            }
                                            else if(resp == -1){
                                                System.out.println("Some errors occurred at the server side. Order not added");
                                            }
                                        }
                                        else{
                                            System.out.println("Product requested does not exist in our list");
                                        }
                                        break;
                                    }
                                    break;
                                }
                                default:
                                    System.out.println("Choice does not match with our parameters"); break;
                            }
                            System.out.println("\nDo you want to continue for customer (y/n)");
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

                        String msgFromServer = (String) clientInputStream.readObject();
                        int respServer = (int) clientInputStream.readObject();

                        if(respServer == 0){
                            System.out.println(msgFromServer);
                            break;
                        }
                        else if(respServer == 1){
                            System.out.println(msgFromServer);
                            while(true){
                                System.out.printf("\n%-22s%-22s", "Users in the database" ,"1");
                                System.out.printf("\n%-22s%-22s","Orders in the queue","2");
                                System.out.printf("\n%-22s%-22s", "Delete user", "3");
                                System.out.printf("\nChoice: ");
                                ch = sc.nextInt();
                                send = "" + ch;
                                clientOutputStream.writeObject(send);

                                switch(ch){
                                    case 1: {
//                                        Company com = (Company) clientInputStream.readObject();
//                                        com.showUsers();

                                        List<String> uids = new ArrayList<String>();
                                        List<String> usernames = new ArrayList<String>();
                                        List<String> names = new ArrayList<String>();
                                        List<String> ages = new ArrayList<String>();

                                        uids = (List<String>) clientInputStream.readObject();
                                        usernames = (List<String>) clientInputStream.readObject();
                                        names = (List<String>) clientInputStream.readObject();
                                        ages = (List<String>) clientInputStream.readObject();

                                        Iterator uidsit = uids.iterator();
                                        Iterator unamesit = usernames.iterator();
                                        Iterator namesit = names.iterator();
                                        Iterator agesit = ages.iterator();
                                        int ite = 0;

                                        System.out.printf("%-22s%-22s%-22s%-22s", "UID", "Username", "Name", "Age");
                                        while(uidsit.hasNext() && unamesit.hasNext() && namesit.hasNext() && agesit.hasNext()){
                                            System.out.printf("\n%-22s%-22s%-22s%-22s", uidsit.next(), unamesit.next(), namesit.next(), agesit.next());
                                            ite++;
                                        }
                                        break;
                                    }

                                    case 2: {
                                        List<String> qids = new ArrayList<String>();
                                        List<String> qunames = new ArrayList<String>();
                                        List<String> qpnames = new ArrayList<String>();

                                        System.out.println("Waiting for server");
                                        qids = (List<String>) clientInputStream.readObject();
                                        qunames = (List<String>) clientInputStream.readObject();
                                        qpnames = (List<String>) clientInputStream.readObject();

                                        System.out.println("Got the data from the server");
                                        Iterator qidit = qids.iterator();
                                        Iterator qunamesit = qunames.iterator();
                                        Iterator qpnamesit = qpnames.iterator();

                                        System.out.println("Iterators created");
                                        int ite = 0;

                                        System.out.printf("\n%-22s%-22s%-22s", "QID", "Username", "Product Ordered");
                                        while(qidit.hasNext() && qunamesit.hasNext() && qpnamesit.hasNext()){
                                            System.out.printf("\n%-22s%-22s%-22s", qidit.next(), qunamesit.next(), qpnamesit.next());
                                            ite++;
                                        }
                                        break;
                                    }
                                    case 3:{
                                        System.out.println("Enter username of user whose details are going to be deleted");
                                        String delUsername = sc.next();
                                        clientOutputStream.writeObject(delUsername);

                                        int resp = (int) clientInputStream.readObject();

                                        if(resp == 0){
                                            System.out.println(delUsername + " does not exist in database\n");
                                        }
                                        else if(resp == 1){
                                            System.out.println(delUsername + " successfully deleted from the database");
                                        }
                                        else if(resp == -1){
                                            System.out.println("Unexpected error occurred while deleting user");
                                        }
                                    }
                                    default:
                                        System.out.println("Choice entered does not match with our parameters."); break;
                                }

                                System.out.println("\n\nDo you want to continue for admin (y/n)");
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
            System.out.println();
            e.printStackTrace();
        }
    }
}