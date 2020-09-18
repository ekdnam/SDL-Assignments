package com.company;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    ServerSocket ss;
    Socket pipe;
    ObjectInputStream serverInputStream;
    ObjectOutputStream serverOutputStream;

    // create constructor
    Server() throws java.io.IOException {
        Company com = new Company(true);
        System.out.println("The company has been created. Now initializing server.");

        System.out.println("Initializing server. Please wait.");
        ServerSocket ss = new ServerSocket(7777);

        System.out.println("Waiting for socket connections");

        Socket pipe = ss.accept();
        System.out.println("Connection from " + pipe);


        // get the input stream from the connected socket
       ObjectInputStream serverInputStream = new ObjectInputStream(pipe.getInputStream());
        System.out.println("Server input stream is up");
        // create a DataInputStream so we can read data from it
        ObjectOutputStream serverOutputStream = new ObjectOutputStream(pipe.getOutputStream());
        System.out.println("Server output stream is up");
    }

//    public static void main(String[] args) throws java.io.IOException{
////        Server server = new Server();
//
//        Company com = new Company();
//        System.out.println("The company has been created. Now initializing server.");
//
//        System.out.println("Initializing server. Please wait.");
//        ServerSocket ss = new ServerSocket(7777);
//
//        System.out.println("Waiting for socket connections");
//
//        Socket pipe = ss.accept();
//        System.out.println("Connection from " + pipe);
//
//        // get the input stream from the connected socket
//        ObjectInputStream serverInputStream = new ObjectInputStream(pipe.getInputStream());
//        System.out.println("Server input stream is up");
//        // create a DataInputStream so we can read data from it
//        ObjectOutputStream serverOutputStream = new ObjectOutputStream(pipe.getOutputStream());
//        System.out.println("Server output stream is up");
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        try {
//            Integer aus = (Integer) server.serverInputStream.readObject();
//            if(aus == 1) {
//                server.run();
//            }
            server.run();
        }
        catch (IOException e) {
            System.out.println("IOException Error while creating server...");
            System.out.println("Error is: " + e);
            System.out.println("Stack trace is: " + e.getStackTrace());
        }
//        }
//        finally{
//            server.serverInputStream.close();
//            server.serverOutputStream.close();
//            server.ss.close();
//        }
    }

    public void chat() throws IOException, ClassNotFoundException {
        String msgin = "", msgout = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            msgin = (String) serverInputStream.readObject();
            if (!msgin.equals("./end")) {
                System.out.println("Client:" + msgin + "\n");
                System.out.printf("Server: ");
                msgout = sc.nextLine();
                sc.nextLine();
                serverOutputStream.writeObject(msgout);
            } else break;
        }
//        break;
    }

    public void run() throws IOException, ClassNotFoundException {
        String choice; int ch;
        while(true){
//            choice = (String)
        }
    }

    public void mainSwitch(Integer aus) throws IOException, ClassNotFoundException {
        switch(aus){
            case 1:
                chat(); break;
        }
    }
}
