package com.company;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DatabaseConnection {

    Connection con;
    String url;
    String dbName;
    Statement conStatement;
    ResultSet resultSet;
    String query;
    Scanner sc;
    String invComma;
    int nUsers;

    List<String> usernames;
    List<String> uids;
    List<String> passwords;
    List<String> names;
    List<String> ages;

    List<String> qids;
    List<String> qunames;
    List<String> qpnames;

    List<String> pids;
    List<String> pnames;
    List<String> pcosts;
    List<String> pqtys;

    public DatabaseConnection(String dbName) {
        try {
            usernames = new ArrayList<String>();
            uids = new ArrayList<String>();
            names = new ArrayList<String>();
            ages = new ArrayList<String>();


            qids = new ArrayList<String>();
            qunames = new ArrayList<String>();
            qpnames = new ArrayList<String>();

            pids = new ArrayList<String>();
            pnames = new ArrayList<String>();
            pcosts = new ArrayList<String>();
            pqtys = new ArrayList<String>();

            nUsers = 1;
            invComma = "\"";
            sc = new Scanner(System.in);
            System.out.println("Name of database is: " + dbName);
            this.url = "jdbc:mysql://localhost:3306/";
            this.url += dbName;
            this.dbName = dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.con = DriverManager.getConnection(url, "root", "mandke");
            System.out.println("Connection to the MySQL database has been successfully established.");
            this.conStatement = con.createStatement();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while connecting to database. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public DatabaseConnection() {
        try {
            usernames = new ArrayList<String>();
            uids = new ArrayList<String>();
            names = new ArrayList<String>();
            ages = new ArrayList<String>();

            qids = new ArrayList<String>();
            qunames = new ArrayList<String>();
            qpnames = new ArrayList<String>();

            pids = new ArrayList<String>();
            pnames = new ArrayList<String>();
            pcosts = new ArrayList<String>();
            pqtys = new ArrayList<String>();

            nUsers = 1;
            invComma = "\"";
            sc = new Scanner(System.in);
            this.dbName = "sdl";
            System.out.println("Name of database is: " + this.dbName);
            this.url = "jdbc:mysql://localhost:3306/";
            this.url += this.dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.con = DriverManager.getConnection(url, "root", "mandke");
            System.out.println("Connection to the MySQL database has been successfully established.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while connecting to the database. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public int deleteUserFromUserTbl(String username){
        try{
            this.query = "select * from user where username = " + invComma + username + invComma + ";";
            this.resultSet = this.conStatement.executeQuery(this.query);

            if(this.resultSet.next()){
                this.query = "delete from user where username = " + invComma + username + invComma + ";";
                this.conStatement.executeUpdate(this.query);
                System.out.println("Record deleted, where username is " + username);
                return 1;
            }
            else{
                System.out.println("User does not exist in database");
                return 0;
            }

        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public void showProducts() {
        try {
            this.query = "select * from productline;";
            this.resultSet = this.conStatement.executeQuery(this.query);

            System.out.printf("\n%-22s%-22s%-22s%-22s", "Product Id", "Name", "Cost", "Quantity");

            while (this.resultSet.next()) {
                System.out.printf("\n%-22s%-22s%-22s%-22s",
                        this.resultSet.getString(1),
                        this.resultSet.getString(2),
                        this.resultSet.getString(3),
                        this.resultSet.getString(4)
                );
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while getting products from database. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public void addProduct() {
        System.out.println("Getting product...");
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nProduct name: ");

        String pname = sc.nextLine();
        System.out.printf("Cost: ");

        Integer pcost = sc.nextInt();
        System.out.printf("Qty: ");

        Integer pqty = sc.nextInt();
        this.query = "insert into productline values(null, \"" + pname + "\", " + pcost + " , " + pqty + " )";

        try {
            System.out.println("Adding product to database");
            conStatement.executeUpdate(this.query);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding product to database. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public String[] getUserDetails(String username){
        try{
            this.query = "select * from user where username = " + invComma + username + invComma + ";";
            this.resultSet = this.conStatement.executeQuery(this.query);

            String[] userInfo = new String[5];

            resultSet.next();
            userInfo[0] = this.resultSet.getString(1);
            userInfo[1] = this.resultSet.getString(2);
            userInfo[2] = this.resultSet.getString(3);
            userInfo[3] = this.resultSet.getString(4);
            userInfo[4] = this.resultSet.getString(5);

            System.out.println("\nUser details are: ");
            System.out.printf("\n%-22s%-22s%-22s%-22s%-22s", "UID", "Username", "Password", "Name", "Age");
            System.out.printf("\n%-22s%-22s%-22s%-22s%-22s", userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);

            return userInfo;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int authorizeUser(String uname, String password){
        try{
            System.out.println("Values recd for OAuth: " + uname + "  " + password);
            this.query = "select password from user where username = " + invComma + uname + invComma + ";";
            System.out.println("Current query is " + this.query);
            this.resultSet = this.conStatement.executeQuery(this.query);
            String actPassword = "";
            if(this.resultSet.next()){
                System.out.println("Username exists");

                System.out.println(this.resultSet.getString(1));
                actPassword = this.resultSet.getString(1);

                if(actPassword != "") {
                    if (actPassword.equals(password)) {
                        System.out.println("Input password matches actual password");
                        return 1;
                    }
                    else{
                        System.out.println("Passwords don't match");
                        return 0;
                    }
                }
            }
            else{
                System.out.println("Username does not exist in db");
                return -3;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Unexpected errors occurred");
        return -5;
    }
    public void addProductFromProduct(Product p) {
        System.out.println("Adding product");
        String pname = p.productName;
        Integer pcost = p.cost;
        Integer pqty = p.qty;

        this.query = "insert into productline values(null, \"" + pname + "\", " + pcost + " , " + pqty + " )";
        try {
            System.out.println("Adding user to database");
            conStatement.executeUpdate(this.query);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding user to database. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public void addUser(){
        System.out.printf("\nUsername: ");
        String username = this.sc.nextLine();
        System.out.printf("Password: ");
        String password = this.sc.nextLine();
        System.out.printf("Name: ");
        String name = this.sc.nextLine();
        System.out.printf("Age: ");
        Integer age = this.sc.nextInt();

        this.query = "insert into user values(null, " + invComma + username + invComma + ", " + invComma + password + invComma + ", ";
        this.query += invComma + name + invComma + ", " + age + ");";

        System.out.println(this.query);

        try {
            System.out.println("Adding user to database");
            conStatement.executeUpdate(this.query);
            System.out.println("User added to database! User id is - " + nUsers);
            nUsers += 1;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. See the error below -> \n " + e);
           e.printStackTrace();
        }
    }

    public void addUserFromUser(User u){
        String username = u.username;
        String password = u.password;
        String name = u.name;
        Integer age = u.age;

        this.query = "insert into user values(null, " + invComma + username + invComma + ", " + invComma + password + invComma + ", ";
        this.query += invComma + name + invComma + ", " + age + ");";

        System.out.println(this.query);

        try {
            System.out.println("Adding user to database");
            conStatement.executeUpdate(this.query);
            System.out.println("User added to database! User id is - " + nUsers);
            nUsers += 1;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public void showUsers() {
        try {
            this.query = "select * from user;";
            this.resultSet = this.conStatement.executeQuery(this.query);
            System.out.printf("\n%-22s%-22s%-22s%-22s%-22s", "UID", "Username", "Password", "Name", "Age");
            int ite = 0;
            while (this.resultSet.next()) {
                Vector<String> rst = new Vector<String>();
                System.out.printf("\n%-22s%-22s%-22s%-22s%-22s",
                        this.resultSet.getString(1),
                        this.resultSet.getString(2),
                        this.resultSet.getString(3),
                        this.resultSet.getString(4),
                        this.resultSet.getString(5)
                );
                ite += 1;
            }

            System.out.println();

        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public int addOrderToQueue(String uname, String pname){
        this.query = "insert into queue values(null, " + invComma + uname + invComma + ", " + invComma + pname + invComma + ")";
//        System.out.println(this.query);

        try {
            System.out.println("\nAdding order to queue");
            conStatement.executeUpdate(this.query);
            System.out.println("Order added to queue!");
            return 1;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. See the error below -> \n " + e);
            e.printStackTrace();
            return -1;
        }
    }
    public void addToQueue(User u, Product p){
        String username = u.username;
        String pname = p.productName;

        this.query = "insert into queue values(null, " + invComma + username + invComma + ", " + invComma + pname + invComma + ")";
//        System.out.println(this.query);

        try {
            System.out.println("\nAdding order to queue");
            conStatement.executeUpdate(this.query);
            System.out.println("Order added to queue!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. See the error below -> \n " + e);
            e.printStackTrace();
        }
    }

    public void getAllProducts(){
        try {
            this.query = "select * from productline;";
            this.resultSet = this.conStatement.executeQuery(this.query);

            if(!pids.isEmpty()) { pids.clear(); }
            if(!pnames.isEmpty()){ pnames.clear(); }
            if(!pcosts.isEmpty()){ pcosts.clear(); }
            if(!pqtys.isEmpty()){ pqtys.clear(); }

            int ite = 0;
            while (this.resultSet.next()) {
                this.pids.add(this.resultSet.getString(1));
                this.pnames.add(this.resultSet.getString(2));
                this.pcosts.add(this.resultSet.getString(3));
                this.pqtys.add(this.resultSet.getString(4));
                ite += 1;
            }

            Iterator pidit = pids.iterator();
            Iterator pnamesit = pnames.iterator();
            Iterator pcostsit = pcosts.iterator();
            Iterator pqtysit = pqtys.iterator();


            System.out.printf("%-22s%-22s%-22s%-22s", "PID", "Name", "Cost", "Qty");
            while(pidit.hasNext() && pnamesit.hasNext() && pcostsit.hasNext() && pqtysit.hasNext()){
                System.out.printf("\n%-22s%-22s%-22s%-22s", pidit.next(), pnamesit.next(), pcostsit.next(), pqtysit.next());
            }

        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public void getAllUsers(){
        try {
            this.query = "select * from user;";
            this.resultSet = this.conStatement.executeQuery(this.query);

            if(!uids.isEmpty()) { uids.clear(); }
            if(!usernames.isEmpty()){ usernames.clear(); }
            if(!names.isEmpty()){ names.clear(); }
            if(!ages.isEmpty()){ ages.clear(); }

            int ite = 0;
            while (this.resultSet.next()) {
                this.uids.add(this.resultSet.getString(1));
                this.usernames.add(this.resultSet.getString(2));
                this.names.add(this.resultSet.getString(4));
                this.ages.add(this.resultSet.getString(5));
                ite += 1;
            }

            Iterator uidsit = uids.iterator();
            Iterator unamesit = usernames.iterator();
            Iterator namesit = names.iterator();
            Iterator agesit = ages.iterator();
            ite = 0;

            System.out.printf("%-22s%-22s%-22s%-22s", "UID", "Username", "Name", "Age");
            while(uidsit.hasNext() && unamesit.hasNext() && namesit.hasNext() && agesit.hasNext()){
                System.out.printf("\n%-22s%-22s%-22s%-22s", uidsit.next(), unamesit.next(), namesit.next(), agesit.next());
                ite++;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public void getQueue(){
        try {
            this.query = "select * from queue;";
            this.resultSet = this.conStatement.executeQuery(this.query);

            if(!qids.isEmpty()) { qids.clear(); }
            if(!qunames.isEmpty()){ qunames.clear(); }
            if(!qpnames.isEmpty()){ qpnames.clear(); }

            int ite = 0;
            while (this.resultSet.next()) {
                this.qids.add(this.resultSet.getString(1));
                this.qunames.add(this.resultSet.getString(2));
                this.qpnames.add(this.resultSet.getString(3));
                ite += 1;
            }

            Iterator qidit = qids.iterator();
            Iterator qunamesit = qunames.iterator();
            Iterator qpnamesit = qpnames.iterator();

            ite = 0;

            System.out.printf("\n%-22s%-22s%-22s", "QID", "Username", "Product Ordered");
            while(qidit.hasNext() && qunamesit.hasNext() && qpnamesit.hasNext()){
                System.out.printf("\n%-22s%-22s%-22s", qidit.next(), qunamesit.next(), qpnamesit.next());
                ite++;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConnection con = new DatabaseConnection("sdl");
        Product p = new Product("Dettol", 120, 500);
        User u = new User(true, "ekdnam", "123", "Aditya", 12);
        con.showUsers();

        System.out.println("\nCurrent users are: ");
        con.getAllUsers();

        System.out.println("\nCurrent products are: ");
        con.getAllProducts();

        System.out.println("\nCurrent queue is: ");
        con.getQueue();
    }

}

class VectorTransfer implements Serializable{
    Vector<Vector<String>> vector2D;
    int lastIndex;
    public VectorTransfer(){
        Vector<Vector<String>> vector2D = new Vector<Vector<String>>();
        lastIndex = -1;
    }

    public void showVector2D(){
        Iterator outerIter = vector2D.iterator();

        int iter = 0;
        while(outerIter.hasNext()){
            Vector<String> innerVector = vector2D.elementAt(iter);
            Iterator innerIter = innerVector.iterator();
            while(innerIter.hasNext()){
                System.out.println();
                System.out.printf("     " + innerIter.next());
            }
            iter += 1;
        }
    }
}