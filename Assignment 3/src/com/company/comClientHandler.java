package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class comClientHandler implements Runnable{
    Socket client;
    ObjectInputStream serverInputStream;
    ObjectOutputStream serverOutputStream;
    DatabaseConnection con;
    Company com;

    Integer ch;
    String choice, y, send, receive;

    public comClientHandler(Socket client) throws java.io.IOException{
        try {
            this.client = client;
            this.serverInputStream = new ObjectInputStream(this.client.getInputStream());
            serverOutputStream = new ObjectOutputStream(this.client.getOutputStream());
            con = new DatabaseConnection("sdl");
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    public void run(){
        try {
//            DatabaseConnection con = new DatabaseConnection("sdl");
            boolean endloop = false;
            mainLoop:
            while (true) {
                String input = (String) serverInputStream.readObject();
                switch(input){
                    case "1": {
                        while (true) {
                            String ch1 = (String) serverInputStream.readObject();
                            switch (ch1) {
                                case "1": {
                                    User u = (User) serverInputStream.readObject();
                                    con.addUserFromUser(u);
                                    serverOutputStream.writeObject("\nUser successfully created!");
                                    break;
                                }
                                case "2": {
                                    String uname = (String) serverInputStream.readObject();
                                    String password = (String) serverInputStream.readObject();

                                    int reconc = con.authorizeUser(uname, password);
                                    serverOutputStream.writeObject(reconc);
                                    if (reconc == -3) {
                                        System.out.println("\nUsername does not exist in database");
                                        break;
                                    } else if (reconc == 0) {
                                        System.out.println("\nPassword doesn't match");
                                    } else if (reconc == -5) {
                                        System.out.println("\nUnhandled exceptions occurred, exiting from this loop");
                                        break;
                                    } else if (reconc == 1) {
                                        System.out.println("\nUser authorized");

                                        String arr[] = new String[5];
                                        arr = con.getUserDetails(uname);
                                        serverOutputStream.writeObject(arr);
                                        break;
                                    }
                                }
                                case "3":{
                                    String uname = (String) serverInputStream.readObject();
                                    String password = (String) serverInputStream.readObject();

                                    int reconc = con.authorizeUser(uname, password);
                                    serverOutputStream.writeObject(reconc);
                                    if (reconc == -3) {
                                        System.out.println("Username does not exist in database");
                                        break;
                                    } else if (reconc == 0) {
                                        System.out.println("Password doesn't match");
                                    } else if (reconc == -5) {
                                        System.out.println("Unhandled exceptions occurred, exiting from this loop");
                                        break;
                                    } else if (reconc == 1) {
                                        System.out.println("User authorized");

                                        con.getAllProducts();
                                        List<String> pnames = new ArrayList<String>();
                                        List<String> pids = new ArrayList<String>();
                                        List<String> pcosts = new ArrayList<String>();
                                        List<String> pqtys = new ArrayList<String>();
                                        pnames = con.pnames;
                                        pids = con.pids;
                                        pcosts = con.pcosts;
                                        pqtys = con.pqtys;

                                        serverOutputStream.writeObject(pids);
                                        serverOutputStream.writeObject(pnames);
                                        serverOutputStream.writeObject(pcosts);
                                        serverOutputStream.writeObject(pqtys);

                                        String username = (String) serverInputStream.readObject();
                                        String pname = (String) serverInputStream.readObject();

                                        int resp = con.addOrderToQueue(uname, pname);

                                        serverOutputStream.writeObject(resp);
                                        break;
                                    }
                                }
                            }
                            String end1 = (String) serverInputStream.readObject();
                            if(end1.equals("n")){
                                System.out.println("\nExiting customer loop");
                                break;
                            }
                        }
                        break;
                    }
                    case "2":{ // admin
                        String adminUname = (String) serverInputStream.readObject();
                        String adminpassword = (String) serverInputStream.readObject();

                        if(adminUname.equals("admin")){
                                if(adminpassword.equals("admin")){
                                    String success = "Username and Password matches! Signing you in";
                                    serverOutputStream.writeObject(success);
                                    serverOutputStream.writeObject(1);

                                    while(true){
                                        String in = (String) serverInputStream.readObject();

                                        switch(in){
                                            case "1":{
                                                System.out.println("\nClient is requesting for users\n");
                                                con.getAllUsers();

                                                List<String> uids = new ArrayList<String>();
                                                List<String> usernames = new ArrayList<String>();
                                                List<String> names = new ArrayList<String>();
                                                List<String> ages = new ArrayList<String>();

                                                uids = con.uids;
                                                usernames = con.usernames;
                                                names = con.names;
                                                ages = con.ages;

                                                serverOutputStream.writeObject(uids);
                                                serverOutputStream.writeObject(usernames);
                                                serverOutputStream.writeObject(names);
                                                serverOutputStream.writeObject(ages);



                                                break;
                                            }
                                            case "2":{
                                                System.out.println("\nClient is requesting for queue\n");
                                                con.getQueue();

                                                List<String> qids = new ArrayList<String>();
                                                List<String> qunames = new ArrayList<String>();
                                                List<String> qpnames = new ArrayList<String>();
//                                                List<String> ages = new ArrayList<String>();

                                                qids = con.qids;
                                                qunames = con.qunames;
                                                qpnames = con.qpnames;
//                                                ages = con.ages;

                                                serverOutputStream.writeObject(qids);
                                                serverOutputStream.writeObject(qunames);
                                                serverOutputStream.writeObject(qpnames);
//                                                serverOutputStream.writeObject(ages);

                                                break;
                                            }
                                            case "3":{
                                                String delUsername = (String) serverInputStream.readObject();
                                                int resp = con.deleteUserFromUserTbl(delUsername);
                                                serverOutputStream.writeObject(resp);
                                                break;
                                            }
                                        }
                                        String end1 = (String) serverInputStream.readObject();
                                        if(end1.equals("n")){
                                            System.out.println("\nExiting customer loop");
                                            break;
                                        }
//                                        break;
                                    }
                                }
                                else{
                                    String error = "Username matches, but password does not match.";
                                    serverOutputStream.writeObject(error);
                                    serverOutputStream.writeObject(0);
                                    break;
                                }
                        }
                        else{
                            String error =  "Admin name does not exist in database";
                            serverOutputStream.writeObject(error);
                            serverOutputStream.writeObject(0);
                            break;
                        }

                        break;
                    }
                    case "3": {
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
                            } else {
                                break;
                            }
                        }
                        System.out.println("\nExiting chatbox");
                        break;
                    }
                }
                System.out.println("\nOut of switch");
//
                y=(String)serverInputStream.readObject();
                System.out.println("\nValue recd: " + y + ".");
                if("n".equals(y)) {
//                if(!y.equals("y")) {
                    serverOutputStream.close();
                    serverInputStream.close();
                    endloop = true;
                    System.out.println("Exiting this client");
                    break mainLoop;
                }
                System.out.println("Main loop");
            }
            serverOutputStream.close();
            serverInputStream.close();
        }
        catch(Exception e){
            System.out.println();
            e.printStackTrace();
        }
    }
}
