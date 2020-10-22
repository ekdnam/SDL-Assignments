package com.company;

import java.sql.*;
import java.util.*;

/**
 * Main connection for database
 */
public class DbCon {
    Connection con;
    String url;

    String dbName;
    Statement conStatement;
    ResultSet resultSet;
    String query;
    PreparedStatement ps;

    Scanner sc;

    /**
     * Constructor for dbcon
     */
    DbCon(){
        try {
            // create scanner object, to be reused throughout the program
            sc = new Scanner(System.in);
            this.dbName = "sdl";
            System.out.println("Name of database is: " + dbName);
            // connection url
            this.url = "jdbc:mysql://localhost:3306/";
            this.url += dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connect to db
            this.con = DriverManager.getConnection(url, "root", "mandke");
            System.out.println("Connection to the MySQL database has been successfully established.");
        }
        catch(java.lang.ClassNotFoundException cnfe){
            System.out.println("Driver not found");
            cnfe.printStackTrace();
        }
        catch(java.sql.SQLException sqlException){
            System.out.println("Not able to connect to the database");
            sqlException.printStackTrace();
        }
    }
    DbCon(String dbName){
        try {
            // create scanner object, to be reused throughout the program
            sc = new Scanner(System.in);
            this.dbName = dbName;
            System.out.println("Name of database is: " + this.dbName);
            // connection url
            this.url = "jdbc:mysql://localhost:3306/";
            this.url += this.dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connect to db
            this.con = DriverManager.getConnection(url, "root", "mandke");
            System.out.println("Connection to the MySQL database has been successfully established.");
        }
        catch(java.lang.ClassNotFoundException cnfe){
            System.out.println("Driver not found");
            cnfe.printStackTrace();
        }
        catch(java.sql.SQLException sqlException){
            System.out.println("Not able to connect to the database");
            sqlException.printStackTrace();
        }
    }
    public int checkWhetherUsernameExists(String username){
        int exists = -1;
        try {
            this.ps = con.prepareStatement("SELECT EXISTS(SELECT * FROM user WHERE username = ?)");
            this.ps.setString(1, username);
            this.resultSet = this.ps.executeQuery();
//            exists = this.resultSet.next().getString();
            while(this.resultSet.next()){
                exists = this.resultSet.getInt(1);
            }
            return exists;
        }
        catch(java.sql.SQLException sqlException){
            System.out.println("An error occurred while executing the statement.");
            sqlException.printStackTrace();
        }
        return exists;
    }
//    public int insertUser(String username, String password){
//        /*
//        * error code 1: username does not exist, successful insertion
//        * error code 0: username exists, insertion failed
//        * error code: -1: error while inserting
//        * */
//        int userExists = checkWhetherUsernameExists(username);
//        if(userExists == 1){
//            System.out.println("Username exists in our database. Please create an account with a new username");
//            return 0;
//        }
//        else if(userExists == -1){
//            System.out.println("An error occurred while searching in the database");
//            return  -1;
//        }
//        else{
//            try {
//                this.ps = con.prepareStatement("INSERT INTO user(username, password) VALUES(?,?)");
//                this.ps.setString(1, username);
//                this.ps.setString(2, password);
//                int update = this.ps.executeUpdate();
//                if(update == 1){
//                    System.out.println("User added! Welcome to KalpanaLabs!\nRemember your password :)");
//                    return 2;
//                }
//                else{
//                    System.out.println("multiplt rows added");
//                    return -1;
//                }
//            }
//            catch(java.sql.SQLException sqlException){
//                System.out.println("error while inserting user into database");
//                sqlException.printStackTrace();
//            }
//        }
//        return -2;
//    }
    public String insertUser(String username, String password){
        /*
         * error code 1: username does not exist, successful insertion
         * error code 0: username exists, insertion failed
         * error code: -1: error while inserting
         * */
        String output = "null";
        int userExists = checkWhetherUsernameExists(username);
        if(userExists == 1){
            output = "Username exists in our database. Please create an account with a new username";
            System.out.println("Username exists in our database. Please create an account with a new username");
            return output;
        }
        else if(userExists == -1){
            output = "An error occurred while searching in the database";
            System.out.println("An error occurred while searching in the database");
            return output;
        }
        else{
            try {
                this.ps = con.prepareStatement("INSERT INTO user(username, password) VALUES(?,?)");
                this.ps.setString(1, username);
                this.ps.setString(2, password);
                int update = this.ps.executeUpdate();
                if(update == 1){
                    output = "User added! Welcome to KalpanaLabs!\nRemember your password :)";
                    System.out.println("User added! Welcome to KalpanaLabs!\nRemember your password :)");
                    return output;
                }
                else{
                    output = "multiple rows added";
                    System.out.println("multiplt rows added");
                    return output;
                }
            }
            catch(java.sql.SQLException sqlException){
                output = "error wihle inserting user into database";
                System.out.println("error while inserting user into database");
                sqlException.printStackTrace();
                return output;
            }
        }
//        return output;
    }
    public String insertUser(String username, String password, String name, String age){
        /*
         * error code 1: username does not exist, successful insertion
         * error code 0: username exists, insertion failed
         * error code: -1: error while inserting
         * */
        String output = "null";
        int userExists = checkWhetherUsernameExists(username);
        if(userExists == 1){
            output = "Username exists in our database. Please create an account with a new username";
            System.out.println("Username exists in our database. Please create an account with a new username");
            return output;
        }
        else if(userExists == -1){
            output = "An error occurred while searching in the database";
            System.out.println("An error occurred while searching in the database");
            return output;
        }
        else{
            try {
                Integer ageInt = Integer.parseInt(age);
                this.ps = con.prepareStatement("INSERT INTO user(username, password, name, age) VALUES(?,?,?,?)");
                this.ps.setString(1, username);
                this.ps.setString(2, password);
                this.ps.setString(3, name);
                this.ps.setInt(4, ageInt);
                int update = this.ps.executeUpdate();
                if(update == 1){
                    output = "User added! Welcome to KalpanaLabs!\nRemember your password :)";
                    System.out.println("User added! Welcome to KalpanaLabs!\nRemember your password :)");
                    return output;
                }
                else{
                    output = "multiple rows added";
                    System.out.println("multiplt rows added");
                    return output;
                }
            }
            catch(java.sql.SQLException sqlException){
                output = "error wihle inserting user into database";
                System.out.println("error while inserting user into database");
                sqlException.printStackTrace();
                return output;
            }
        }
//        return output;
    }
    public String[] showUserDetails(String username){
        String[] userDetails = new String[5];
        int userExists = checkWhetherUsernameExists(username);
        try {
            if (userExists == 1) {
                this.ps = this.con.prepareStatement("SELECT * FROM user WHERE username = ?");
                this.ps.setString(1, username);
                this.resultSet = this.ps.executeQuery();
                while(this.resultSet.next()){
                    userDetails[0] = this.resultSet.getString(1);
                    userDetails[1] = this.resultSet.getString(2);
                    userDetails[2] = this.resultSet.getString(3);
                    userDetails[3] = this.resultSet.getString(4);
                    userDetails[4] = this.resultSet.getString(5);
                }
//                int i = 0;
//                while(i)
            }
            else{
                int i = 0;
                while(i < 5){
                    userDetails[i] = "null";
                    i++;
                }
            }
        }
        catch(java.sql.SQLException sqlException){
            System.out.println("Error while accessing database");
            sqlException.printStackTrace();
        }
        return userDetails;
    }
    public int insertUser(String username, String password, String name){
        /*
         * error code 1: username does not exist, successful insertion
         * error code 0: username exists, insertion failed
         * error code: -1: error while inserting
         * */
        int userExists = checkWhetherUsernameExists(username);
        String output;
        if(userExists == 1){
            output = "Username exists in our database. Please create an account with a new username";
            System.out.println("Username exists in our database. Please create an account with a new username");
            return 0;
        }
        else if(userExists == -1){
            output = "An error occurred while searching in the database";
            System.out.println("An error occurred while searching in the database");
            return  -1;
        }
        else{
            try {
                this.ps = con.prepareStatement("INSERT INTO user(username, password, name) VALUES(?,?, ?)");
                this.ps.setString(1, username);
                this.ps.setString(2, password);
                this.ps.setString(3, name);
                int update = this.ps.executeUpdate();
                if(update == 1){
                    output = "User added! Welcome to KalpanaLabs!\nRemember your password :)";
                    System.out.println("User added! Welcome to KalpanaLabs!\nRemember your password :)");
                    return 2;
                }
                else{
                    System.out.println("multiplt rows added");
                    return -1;
                }
            }
            catch(java.sql.SQLException sqlException){
                output = "error while inserting into database";
                System.out.println("error while inserting into database");
                sqlException.printStackTrace();
            }
        }
        return -2;
    }
    public void deleteUser(String username){
        String output = "null";
        int userExists = checkWhetherUsernameExists(username);
        if(userExists == 1){
            try {
                this.ps = con.prepareStatement("DELETE FROM queue WHERE username = ?");
                this.ps.setString(1, username);
                int r = this.ps.executeUpdate();
                if(r > 0) {
                    output = "Your " + r + " order(s) have been deleted.\n";
                }
                else{
                    output = "";
                }

                this.ps = con.prepareStatement("DELETE FROM user where USERNAME = ?");
                this.ps.setString(1, username);
                r = this.ps.executeUpdate();

                output += "User " +  username + " sucessfully deleted";
                System.out.println(output);
            }
            catch(java.sql.SQLException sqlException) {
                output = "Error occurred while deleting user";
                System.out.println(output);
                sqlException.printStackTrace();
            }
        }
        else{
            output = "The username does not exists in database. Thus can't be deleted.";
            System.out.println(output);
        }
    }
    public void updateUserName(String username, String name){
        String out = "null";
        try{
            int userExists = checkWhetherUsernameExists(username);
            if (userExists == 1) {
                this.ps = this.con.prepareStatement("UPDATE USER SET name = ? WHERE username = ?");
                this.ps.setString(1, name);
                this.ps.setString(2, username);
                int r = this.ps.executeUpdate();
                out = "User details successfully updated!";
                System.out.println(out);
            }
            else{
                out = "Username " + username + " does not exist in database";
                System.out.println(out);
            }

        }
        catch(java.sql.SQLException sqlException){
            out = "An error occurred while updating user details";
            System.out.println(out);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateUserAge(String username, Integer age){
        String out = "null";
        try{
            int userExists = checkWhetherUsernameExists(username);
            if (userExists == 1) {
                this.ps = this.con.prepareStatement("UPDATE USER SET age = ? WHERE username = ?");
                this.ps.setInt(1, age);
                this.ps.setString(2, username);
                int r = this.ps.executeUpdate();
                out = "User details successfully updated!";
                System.out.println(out);
            }
            else{
                out = "Username " + username + " does not exist in database";
                System.out.println(out);
            }
        }
        catch(java.sql.SQLException sqlException){
            out = "An error occurred while updating user details";
            System.out.println(out);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        DbCon con = new DbCon();
//        int returnValue = con.checkWhetherUsernameExists("ekdnam");
//        System.out.println(returnValue);
        con.deleteUser("ekdnam");
        con.updateUserName("a", "b");
        con.updateUserAge("a", 15);
    }
}
