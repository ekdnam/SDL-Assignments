package com.company;

import java.io.*;
import java.util.*;

public class Employee implements Serializable {

    private String name,phone,address,emailid;
    private int units,bill,id;
    private boolean flag=true;

    Employee(String name,String phone,String address,String emailid,Integer id) {
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.address=address;
        this.emailid=emailid;
        this.units=0;
        this.bill=0;
        flag=false;
    }

    public String getEmployeePhone() {
        return phone ;
    }
    //
//   public void setEmployeeNumber(int num) {
//      employeeNumber = num;
//   }
//
    public String getEmployeeName() {
        return this.name ;
    }
    public Integer getEmployeeID() {
        return this.id ;
    }
    public Integer getBill() {
        return this.bill;
    }
    public boolean getFlag() {
        return flag;
    }
    public void display() {
        System.out.println("Customer Name="+name);
        System.out.println("Customer PhoneNo="+phone);
        System.out.println("Customer address="+address);
        System.out.println("Customer emailid="+emailid);
        if(this.flag) {
            System.out.println("Customer units consumed="+ this.units);
            System.out.println("Customer bill="+ this.bill);
        }else {
            System.out.println("You have Payed bill !!");
        }

    }

    public void setEmployeeName(String name) {
        this.name = name;
    }
    public void setEmployeePhone(String phone) {
        this.phone = phone;
    }
    public void setEmployeeAddress(String address) {
        this.address = address;
    }
    public void setEmployeeEmailID(String email) {
        this.emailid = email;
    }

    public String payBill(int amount){
//	   System.out.println(flag);
        if(this.flag) {
//	   System.out.println("You have consumeed "+units+" units of electricity");
//	   System.out.println("You have to pay "+bill+" Rs");
//	   int amount=new Scanner(System.in).nextInt();
            if(amount==bill) {
                this.flag=false;
                this.bill=-1;
                this.units=-1;
                return "You have payed bill successfully";

            }else {
                return "Enter right Amount";
            }
        }
        else {
            return "You have Payed bill for this month";
        }

    }
    public String gatBillDetail() {
        String y="You have consumeed "+units+" units of electricity " + "You have to pay "+bill+" Rs";
        return y;
    }
    public void setUnits(int units) {
//	   Scanner sc = new Scanner(System.in);
//		System.out.println("Enter No of units consumed");
        this.units=units;
        this.bill=(this.units*50);
        this.bill+=this.bill*0.14;
        this.flag=true;
    }
//   public void pay
}