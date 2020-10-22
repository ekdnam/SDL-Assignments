package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/*
 * class Company
 *
 * attrs:
 * companyName: String. The name of the company.
 * database: TreeMap. contains the info about users.
 * line: ProductLine. contains all the Product(s)
 * pq: PriorityQueue. a queue that has all the orders.
 * users: int. number of users.
 * password: String. the password while logging in.
 * */

/*
 * functions()
 *
 * 1. void addOffers() -> Company
 * 2. void showOffers() -> User
 * 3. void getOrder() -> User
 * 4. int companySignIn() -> Company
 * 5. int userSignIn() -> Company
 * 6. void createLine() -> Company
 * 7. void createUser() -> Company
 * 8. void showQueue() -> Company
 * 9. void showUsers() -> Company
 * */

public class Company implements Serializable {
    DatabaseConnection con;
    String companyName;
    TreeMap<String, User> database;
    ProductLine line;
    Vector offerList;
    PriorityQueue<Order> pq;
    int users;
    String password;
    Vector usernames;
    Vector names;
    Vector priorities;
    Vector ages;

    public Company() throws IOException{
//        DatabaseConnection con = new DatabaseConnection();
        Vector usernames = new Vector();
        Vector names = new Vector();
        Vector priorities = new Vector();
        Vector ages = new Vector();
        Enumeration e;
        Scanner sc = new Scanner(System.in);
        this.companyName = "admin";
        this.password = "admin";
        System.out.println("\nCompany account created!");
        this.line = new ProductLine();
        this.database = new TreeMap<String, User>();
        this.users = 0;
        offerList = new Vector();
        offerList.add("Special offers");
        offerList.add("Premium membership");
        offerList.add("Fast delivery");
        e = offerList.elements();
        System.out.println("We currently have the following offers! ");
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
        this.pq = new PriorityQueue<Order>(100, new TheComparator());
    }

    // generate company from template, saves time in company initialization
    public Company(boolean value) {
//        DatabaseConnection con = new DatabaseConnection();
        if (!value) {
            return;
        }
        System.out.println("Generating company from template... PLease wait...");
        this.companyName = "admin";
        this.password = "admin";
        System.out.println("Username: admin\nPassword: admin");
        System.out.println("\nCompany Account created!");

        this.database = new TreeMap<String, User>();
        System.out.println("Do you want to create the ProductLine from template?(1/0)");

        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();

        if (choice == 0) {
            this.line = new ProductLine();
        } else if (choice == 1) {
            System.out.println("\nInitializing products from prebuilt preferences...\n\n");
            Integer nProducts = 5;
            String[] productNames = new String[]{"Dettol", "Lifebuoy", "Vatika", "Himalaya", "Vivo1802"};
            Integer[] costs = new Integer[]{12, 24, 27, 100, 10000};
            Integer[] qtys = new Integer[]{100, 249, 234, 25, 142};

            this.line = new ProductLine(nProducts, productNames, costs, qtys);

            this.line.showProducts();
        }

        Enumeration e;
        this.users = 0;
        offerList = new Vector();
        offerList.add("Special offers");
        offerList.add("Premium membership");
        offerList.add("Fast delivery");
        e = offerList.elements();
        System.out.println("\nWe currently have the following offers! \n");
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
        System.out.println();

        this.pq = new PriorityQueue<Order>(100, new TheComparator());
    }

    /*
     * void addOffer()
     *
     * add offers to the offers-vector
     * */
    public void addOffer() {
        System.out.println("The new offer: ");
        Scanner sc = new Scanner(System.in);
        String offer = sc.nextLine();
        this.offerList.add(offer);
    }

    /*
     * void showOffers()
     *
     * show all the offers
     * */
    public void showOffers() {
        Enumeration e = this.offerList.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }


    /*
     * void createLine()
     *
     *
     * get all the products
     * */
    public void createLine() {
        line.getProducts();
    }

    /*
     * void showQueue()
     *
     * show the orders queue
     * */
    public void showQueue() {
        if (!pq.isEmpty()) {
            System.out.println("The current queue is: ");
            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "ProductName", "Cost");
            for (Order o : pq) {
                System.out.printf("%-22s%-22s%-22s%-22s\n", o.u.username, o.u.name, o.p.productName, o.p.cost);
            }
        } else {
            System.out.println("The delivery queue is empty...");
        }
    }

    /*
     * void showUsers()
     *
     * show all the users currently in the database
     * */
    public void showUsers() {
        if (database.isEmpty()) {
            System.out.println("Users not added to database");
        } else {
            System.out.print("Current users of the system are: \n");
            System.out.printf("%-22s%-22s%-22s%-22s\n", "Username", "Name", "Age", "Priority");
            for (Map.Entry<String, User> entry : database.entrySet()) {
                System.out.printf("%-22s%-22s%-22s%-22s\n", entry.getKey(), entry.getValue().name, entry.getValue().age, entry.getValue().priority);
            }
        }
    }
}


/*
 * class Order
 *
 * template of order
 * */
class Order implements Serializable {
    User u;
    Product p;
    int priority;

    Order(User u, Product p) {
        this.u = u;
        this.p = p;
        this.priority = u.priority;
    }
}

// comparator
class TheComparator implements Comparator<Order>, Serializable {
    @Override
    public int compare(Order a, Order b) {
        if (a.priority > b.priority) {
            return -1;
        }
        if (a.priority < b.priority) {
            return 1;
        }
        return 0;
    }
}

class DataBase {
    TreeMap<String, User> database;

    public DataBase(TreeMap<String, User> db) {
        this.database = db;
    }
}