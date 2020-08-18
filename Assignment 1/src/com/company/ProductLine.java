package com.company;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
public class ProductLine{
    HashMap<String, Product>Products;
    int nProducts;

    public void getProducts(){
        int nProducts; Product temp;
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of products to be created: ");
        nProducts = sc.nextInt();
        this.Products = new HashMap<String, Product>();
        this.nProducts = nProducts;

        for(int i = 0; i < nProducts; i++){
            temp = createProduct();
            Product x = new Product(temp);
//
//            System.out.println("Qty of product: ");
//            Integer x = sc.nextInt();

            Products.put(x.productName, x);
        }
    }

    public Product createProduct(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Product name: ");
        String name = sc.nextLine();

        System.out.println("Cost: ");
        Integer cost = sc.nextInt();

        Integer qty = getQty();
        Product temp = new Product(name, cost, qty);
        return temp;
    }

    public Integer getQty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qty of product: ");
        Integer x = sc.nextInt();
        return x;
    }

    public Product search(String productName){
        Product x = Products.get(productName);
        return x;
    }

    public void showProducts(){
        Iterator it = Products.entrySet().iterator();
        System.out.println("We have the following products! ");
        System.out.printf("%-22s%-22s%-22s\n", "Product Name", "Cost", "Qty");
        Products.forEach((k, p) -> System.out.printf("%-22s%-22s%-22s\n", k, p.cost, p.qty));
//        Products.forEach((k, p) -> System.out.printf(k + "     " + p.cost + "      " + p.qty));
    }
}


class Product{
    String productName;
    int cost;
    int qty;
    Product(Product x){
        this.productName = x.productName;
        this.cost = x.cost;
        this.qty = x.qty;
    }

    public Product(String productName, int cost, int qty){
        this.productName = productName;
        this.cost = cost;
        this.qty = qty;
    }

    public void discountOnProduct(int disc){
        this.cost = this.cost*(1-disc);
    }

}
