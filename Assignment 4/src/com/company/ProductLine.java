package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
 * class ProductLine
 *
 * Contains the objects of class Product stored in a HashMap
 *   <key, value> -> <ProductName, Product>
 */
public class ProductLine implements Serializable {
    HashMap<String, Product> Products;
    int nProducts;

    public ProductLine() {
        getProducts();
    }

    public ProductLine(ProductLine x) {
        this.Products.putAll(x.Products);
        this.nProducts = x.nProducts;
    }

    public ProductLine(Integer nProducts, String[] productNames, Integer[] costs, Integer[] qtys) {
        Integer nProductNames = productNames.length;
        Integer nCosts = costs.length;
        Integer nQtys = qtys.length;

        boolean c1 = nProductNames.equals(nProducts);
        boolean c2 = nCosts.equals(nProducts);
        boolean c3 = nQtys.equals(nProducts);

        boolean c4 = nCosts.equals(nProductNames);
        boolean c5 = nQtys.equals(nProductNames);

        boolean c6 = nQtys.equals(nCosts);

        if (!(c1 && c2 && c3 && c4 && c5 && c6)) {
            System.out.println("The values in the arrays do not have the same sizes... Auto-exiting");
            return;
        }

        this.Products = new HashMap<String, Product>();

        for (int index = 0; index < nProducts; index++) {
            Product x = new Product(productNames[index], costs[index], qtys[index]);

            Products.put(productNames[index], x);
        }
        System.out.println("ProductLine successfully created!");
    }

    public void getProducts() {
        int nProducts;
        Product temp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of products to be created: ");
        nProducts = sc.nextInt();
        this.Products = new HashMap<String, Product>();
        this.nProducts = nProducts;
        for (int i = 0; i < nProducts; i++) {
            temp = createProduct();
            Product x = new Product(temp);
            Products.put(x.productName, x);
        }
    }

    /*
     * Product createProduct()
     *
     * Creates a product by accepting the product name, cost, qty of product
     */
    public Product createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Product name: ");
        String name = sc.nextLine();
        System.out.println("Cost: ");
        Integer cost = sc.nextInt();
        Integer qty = getQty();
        Product temp = new Product(name, cost, qty);
        return temp;
    }

    /*
     * Integer getQty()
     *
     * returns the qty of product
     */
    public Integer getQty() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qty of product: ");
        Integer x = sc.nextInt();
        return x;
    }

    /*
     * Product search(productName)
     *
     * returns a Product with specific key (productName) after searching for it in the HashMap
     *
     *  */
    public Product search(String productName) {
        if (Products.containsKey(productName)) {
            Product x = Products.get(productName);
            return x;
        } else {
            return null;
        }
    }

    /*
     * show the products
     */
    public void showProducts() {
        Iterator it = Products.entrySet().iterator();
        System.out.println("We have the following products! ");
        System.out.printf("%-22s%-22s%-22s\n", "Product Name", "Cost", "Qty");
        Products.forEach((k, p) -> System.out.printf("%-22s%-22s%-22s\n", k, p.cost, p.qty));
    }
}


class Product implements Serializable {
    String productName;
    int cost;
    int qty;
//    Integer pid;

    Product(Product x) {
//        this.pid = x.pid;
        this.productName = x.productName;
        this.cost = x.cost;
        this.qty = x.qty;
    }

    public Product(String productName, int cost, int qty) {
        this.productName = productName;
        this.cost = cost;
        this.qty = qty;
    }

//    public Product(boolean value, String productName, int cost, int qty) {
//        this.productName = productName;
//        this.cost = cost;
//        this.qty = qty;
//    }

    public void discountOnProduct(int disc) {
        this.cost = this.cost * (1 - disc);
    }

    public void Product() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nProduct name: ");
        String pname = sc.nextLine();
        System.out.printf("Cost: ");
        Integer pcost = sc.nextInt();
        System.out.printf("Qty: ");
        Integer pqty = sc.nextInt();

        this.productName = pname;
        this.cost = pcost;
        this.qty = pqty;
    }

}
