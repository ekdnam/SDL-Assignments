package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    Socket socket;
    Scanner sc;
    ObjectOutputStream clientOutputStream;
    ObjectInputStream clientInputStream;

    Client() throws IOException {
        sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected to host server!");
        ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Client output stream is up");
        ObjectInputStream clientInputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Client input stream is up");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Socket socket = new Socket("localhost", 7777);
//        System.out.println("Connected to host server!");

//        ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        System.out.println("Client output stream is up");
//        ObjectInputStream clientInputStream = new ObjectInputStream(socket.getInputStream());
//        System.out.println("Client input stream is up");

        Client client = new Client();

        // running the client server
        try {
            client.run();
        }
        catch(Exception e){
            System.out.println("Exception occurred: " + e);
        }
        finally{
            client.clientOutputStream.close();
            client.clientInputStream.close();
            client.socket.close();
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        String y, send;
        Integer aus, aus2;
        boolean endLoop = false;
        while (true) {
            showMainOptions();
            System.out.println("Choice: ");
            aus = sc.nextInt();
            this.clientOutputStream.writeObject(aus);
            if (aus == 2) {
                endLoop = true;
                break;
            }
            switch (aus) {
                case 1: {
                    System.out.println("Initializing chatting application");
                    sc.nextLine();
                    String inMsg = "", outMsg = "";
                    System.out.println("You: ");
                    outMsg = sc.nextLine();
                    while (true) {
                        if (!outMsg.equals("end")) {
                            clientOutputStream.writeObject(outMsg);
                            inMsg = (String) clientInputStream.readObject();
                            System.out.println("Server: " + inMsg);
                            System.out.println("You: ");
                            outMsg = sc.nextLine();
                        } else {
                            clientOutputStream.writeObject(outMsg);
                            break;
                        }
                    }
                }
                default: {
                    System.out.println("Valid option not entered. Specified operation not performed.");
                }
            }
            System.out.println("Do you want to continue for admin/customer (y/n)");
            y=sc.next();
            send=y;
            clientOutputStream.writeObject(send);
            if(!"y".equals(y)) break;
        }
        clientOutputStream.close();
        clientInputStream.close();
    }

    public void showMainOptions(){
        System.out.println("Chatbox: 1");
        System.out.println("Exit: 2");
    }

    public void mainSwitch(Integer choice) throws IOException, ClassNotFoundException {
//    public boolean mainSwitch(Integer choice) throws IOException {
        switch(choice){
            case 1:
                chat(); break;
        }
    }

    public void chat() throws IOException, ClassNotFoundException {
        sc.nextLine();
        String msgin="",msgout="";
        System.out.println("Write anything");
        msgout=sc.nextLine();
        while(true) {
            if(!msgout.equals("./end")) {

                clientOutputStream.writeObject(msgout);
                msgin=(String)clientInputStream.readObject();
                System.out.println("From Server: "+msgin+"\n");
                System.out.println("Write another one");
                msgout=sc.nextLine();
            }
            else {
                clientOutputStream.writeObject(msgout);
                break;
            }
        }
    }
}

//    public void chat() throws IOException {
//        new Thread(new ReceivedMessagesHandler(clientInputStream));
//
//        // read messages from keyboard and send to server
//        System.out.println("Send messages: ");
//        PrintStream output = new PrintStream(this.socket.getOutputStream());
//        while (sc.hasNextLine()) {
//            output.println("Client: " + sc.nextLine());
//        }
//        output.close();
//        sc.close();
//    }
//}
//
//class ReceivedMessagesHandler implements Runnable {
//
//    private InputStream server;
//
//    public ReceivedMessagesHandler(InputStream server) {
//        this.server = server;
//    }
//
//    @Override
//    public void run() {
//        // receive server messages and print out to screen
//        Scanner s = new Scanner(server);
//        while (s.hasNextLine()) {
//            System.out.println(s.nextLine());
//        }
//        s.close();
//    }
//}
