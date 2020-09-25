package com.company;


import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    public static ArrayList<comClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws java.io.IOException{
        DatabaseConnection con = new DatabaseConnection("sdl");
        ServerSocket socketConnection = new ServerSocket(11111);
        Company com = new Company(true);
        ExecutorService pool = Executors.newFixedThreadPool( 10);

        while(true)
        {
            System.out.println("Server Waiting");
            Socket client=socketConnection.accept();
            System.out.println("Server is connected to client");
            comClientHandler clientThread = new comClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
