//package dsk;
//
//package com.company;
//
//import java.io.*;
//import java.net.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//import java.util.*;
//import java.sql.*;
//
//class EmpComparator implements Comparator<Employee>
//{
//    public int compare(Employee e1,Employee e2)
//    {
//        if(e1.getBill() < e2.getBill())
//        {
//            return 1;
//        }
//        else
//        {
//            return -1;
//        }
//    }
//
//}
//
//public class ClientHandler implements Runnable {
//
//    Socket client;
//    ObjectInputStream serverInputStream;
//    ObjectOutputStream serverOutputStream;
//    Employee employee = null;
//    HashMap<Integer,Employee> hm=new HashMap<Integer,Employee>();
//    PriorityQueue<Employee> pq=new PriorityQueue<Employee>(new EmpComparator());
//
//    Integer ch;
//    String choice,y,send,recieve;
//    Statement st,st1,st2,st3;
//    ResultSet rs;
//    public static <K, V> K getKey(Map<K, V> map, V value) {
//        for (Map.Entry<K, V> entry : map.entrySet()) {
//            if (value.equals(entry.getValue())) {
//                return entry.getKey();
//            }
//        }
//        return null;
//    }
//    public ClientHandler(Socket clientSocket) throws IOException, ClassNotFoundException, SQLException {
//        this.client=clientSocket;
//        serverInputStream = new
//                ObjectInputStream(this.client.getInputStream());
//        serverOutputStream = new
//                ObjectOutputStream(this.client.getOutputStream());
//        st=Server.con.createStatement();
//        st1=Server.con.createStatement();
//        st2=Server.con.createStatement();
//        st3=Server.con.createStatement();
//
//    }
//
//    public void run() {
//
//        try {
//            while(true) {
//                choice=(String)serverInputStream.readObject();
//                ch=Integer.parseInt(choice);
//                switch(ch) {
//                    case 1:{
//                        while(true) {
//                            choice=(String)serverInputStream.readObject();
//                            ch=Integer.parseInt(choice);
//                            switch(ch) {
//                                case 1:{
////	        	    				ResultSet rs=st.executeQuery("select * from author where author_no=1");
////	        	    				rs.next();
////	        	    				int id=rs.getInt("author_no");
////	        	    				String  s=rs.getString("author_name");
////	        	    				String country=rs.getString("country");
//////	        	    				System.out.println(id + " "+s+" "+" "+country);
//                                    employee = (Employee )serverInputStream.readObject();
//                                    st.execute("insert into Customer(name,emailid,address,phone) "+"values( "+"'"+employee.getEmployeeName()+"'"+" , "+"'"+employee.getEmployeeEmailID()+"'" +" , "+"'"+ employee.getEmployeeAddress()+"'"+" , "+"'"+employee.getEmployeePhone()+"'"  +  " )");
//
////	        	    				hm.put(employee.getEmployeeID(), employee);
//                                    rs=st.executeQuery("select id from Customer where name="+"'"+employee.getEmployeeName()+"'");
//                                    rs.next();
//
//                                    send="Congrats!! You are our new customer and please do remember this id="+rs.getInt("id");
//
//                                    serverOutputStream.writeObject(send);
//                                    break;
//                                }
//                                case 2:{
//                                    recieve=(String)serverInputStream.readObject();
//                                    int id=Integer.parseInt(recieve);
////	        	    				Employee E=(Employee)hm.get(id);
//                                    rs=st.executeQuery("select * from Customer where id="+id);
//
//                                    if(rs.next()) {
//                                        send="Customer Name="+rs.getString("name")+"\nCustomer phone="+rs.getString("phone")+"\nCustomer EmailID="+rs.getString("emailid")+"\nCustomer address="+rs.getString("address");
//                                        serverOutputStream.writeObject(send);
//                                    }else {
//                                        send="Sry you are not our customer if any issue pls put it in chat box";
//                                        serverOutputStream.writeObject(send);
//                                    }
//                                    break;
//                                }
//                                case 3:{
//                                    recieve=(String)serverInputStream.readObject();
//                                    int id=Integer.parseInt(recieve);
//                                    rs=st.executeQuery("select * from Customer where id="+id);
//                                    if(rs.next()) {
//                                        send="Customer Name="+rs.getString("name")+"\nCustomer phone="+rs.getString("phone")+"\nCustomer EmailID="+rs.getString("emailid")+"\nCustomer address="+rs.getString("address");
//                                        serverOutputStream.writeObject(send);
//                                        while(true) {
//                                            choice=(String)serverInputStream.readObject();
//                                            ch=Integer.parseInt(choice);
//                                            switch(ch) {
//                                                case 1: {
//                                                    recieve=(String)serverInputStream.readObject();
//                                                    st.execute("update Customer set name="+"'"+recieve+"'"+" where id="+id);
//                                                    send="Your name is updated successfully!!";
//                                                    serverOutputStream.writeObject(send);
//                                                    break;
//                                                }
//                                                case 2: {
//                                                    recieve=(String)serverInputStream.readObject();
//                                                    st.execute("update Customer set phone="+"'"+recieve+"'"+" where id="+id);
//                                                    send="Your phone number is updated successfully!!";
//                                                    serverOutputStream.writeObject(send);
//                                                    break;
//                                                }
//                                                case 3: {
//                                                    recieve=(String)serverInputStream.readObject();
//                                                    st.execute("update Customer set emailid="+"'"+recieve+"'"+" where id="+id);
//                                                    send="Your emailID is updated successfully!!";
//                                                    serverOutputStream.writeObject(send);
//                                                    break;
//                                                }
//                                                case 4: {
//                                                    recieve=(String)serverInputStream.readObject();
//                                                    st.execute("update Customer set address="+"'"+recieve+"'"+" where id="+id);
//                                                    send="Your address is updated successfully!!";
//                                                    serverOutputStream.writeObject(send);
//                                                    break;
//                                                }
//                                            }
//                                            y=(String)serverInputStream.readObject();
//                                            if(!y.equals("y"))
//                                                break;
//
//                                        }
//                                        rs=st.executeQuery("select * from Customer where id="+id);
//                                        rs.next();
//                                        send="Customer Name="+rs.getString("name")+"\nCustomer phone="+rs.getString("phone")+"\nCustomer EmailID="+rs.getString("emailid")+"\nCustomer address="+rs.getString("address");
//                                        serverOutputStream.writeObject(send);
//
//
//                                    }else {
//                                        send="Sry you are not able to edit because you are not our customer if any issue pls put it in chat box";
//                                        serverOutputStream.writeObject(send);
//                                    }
////	        	    				serverOutputStream.writeObject(E);
//                                    break;
//                                }
//                                case 4:{
//                                    recieve=(String)serverInputStream.readObject();
//                                    Integer id=Integer.parseInt(recieve);
//                                    rs=st.executeQuery("select id from Customer where id="+id);
//                                    if(rs.next()) {
//                                        ArrayList<Integer> bill_ids=new ArrayList<>();
//                                        ArrayList<Integer> date=new ArrayList<>();
//                                        ArrayList<String> Insert=new ArrayList<>();
//                                        HashMap<String,Integer> hm=new HashMap<>();
//                                        send="Yes your are able to pay bill";
//                                        serverOutputStream.writeObject(send);
//                                        String query="select c_a_id , bill_id from records where c_id="+id+" and billpaymentdone=0";
//                                        rs=st.executeQuery(query);
//                                        while(rs.next()) {
//                                            bill_ids.add(rs.getInt("bill_id"));
//                                            date.add(rs.getInt("c_a_id"));
//
////	        	    						System.out.println(x);
//                                        }
////	        	    					query="select unit,bill from admin where aid=";
//                                        Iterator it1=bill_ids.iterator();
//                                        Iterator it2=date.iterator();
//                                        int i;
//                                        while(it1.hasNext() && it2.hasNext()) {
//                                            i = (Integer)it1.next();
//                                            query="select unit,bill from admin where aid="+i;
//                                            ResultSet rs2=st.executeQuery(query);
//                                            if(rs2.next()) {
//                                                ResultSet rs3=st1.executeQuery("select * from records where c_a_id="+it2.next());
//                                                rs3.next();
//                                                String insert="You have not payed bill for date "+rs3.getString("PayBillDate")+"\nAmount of bill is "+rs2.getInt("bill")+" Rs" + "\nNo. of units consumed="+rs2.getInt("unit");
//                                                Insert.add(insert);
//                                                hm.put(rs3.getString("PayBillDate"),i);
//                                            }
//                                        }
//                                        serverOutputStream.writeObject(Insert);
//
//                                        recieve = (String)serverInputStream.readObject();
//
//                                        rs=st.executeQuery("select * from records where PayBillDate="+recieve);
//                                        if(rs.next()) {
//                                            send="Enter exact amount for corresponding date";
//                                            serverOutputStream.writeObject(send);
//                                            int bill=(Integer)serverInputStream.readObject();
////	        	    						it1=bill_ids.iterator();
//                                            int billid=hm.get(recieve);
//                                            query="select * from admin where aid="+billid;
//                                            rs=st.executeQuery(query);
//                                            rs.next();
//                                            if(bill==rs.getInt("bill")) {
//                                                query="update records set billpaymentdone=1 where bill_id="+billid;
//                                                st1.execute(query);
//                                                send="Bill payed successfully for given date";
//                                                serverOutputStream.writeObject(send);
//
//                                            }else {
//                                                send="Bill is not payed successfully\nPls Enter exact amount to complete transaction";
//                                                serverOutputStream.writeObject(send);
//                                            }
//                                        }else {
//                                            send="Enter exact date for next time!!";
//                                            serverOutputStream.writeObject(send);
//                                        }
//
//
//
//                                    }
//                                    else {
//                                        send="Sry!! You made mistake while entering your id\nPlease check";
//                                        serverOutputStream.writeObject(send);
//                                    }
//                                    break;
//                                }
//                            }
//                            y=(String)serverInputStream.readObject();
//                            if(!y.equals("y"))
//                                break;
//                        }
//
//
//                        break;
//                    }
//                    case 2:{
//                        while(true) {
//                            choice=(String)serverInputStream.readObject();
//                            ch=Integer.parseInt(choice);
//                            switch(ch) {
//                                case 1:{
//                                    while(true) {
//                                        recieve=(String)serverInputStream.readObject();
//                                        int id=Integer.parseInt(recieve);
////			        	    				Employee E=(Employee)hm.get(id);
//                                        rs=st.executeQuery("select * from Customer where id="+id);
//
//                                        if(rs.next()) {
//                                            send="Yes Customer exist!";
//
//                                            serverOutputStream.writeObject(send);
//                                            Integer units=Integer.parseInt((String)serverInputStream.readObject());
//                                            String date=(String)serverInputStream.readObject();
////			        	    					serverOutputStream.writeObject(send);
//                                            int bill=units*50;
//                                            bill+=bill*0.14;
//
//                                            String query="insert into Admin(unit,bill) values(?,?)";
//
//                                            PreparedStatement st1= Server.con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
//
//                                            st1.setInt(1, units);
//                                            st1.setInt(2, bill);
////			        	    					st1.setString(3, date);
//
//                                            st1.execute();
//                                            rs=st1.getGeneratedKeys();
//                                            rs.next();
//
//                                            String query1="insert into records(c_id,bill_id,PayBillDate) values(?,?,?)";
//                                            PreparedStatement st2= Server.con.prepareStatement(query1,PreparedStatement.RETURN_GENERATED_KEYS);
//                                            st2.setInt(1, id);
//                                            st2.setInt(2, rs.getInt(1));
//                                            st2.setString(3, date);
//                                            st2.execute();
//
////				        	    				st.execute("insert into Admin(id) "+"values( "+id+" )");//'"+employee.getEmployeeName()+"'"+" , "+"'"+employee.getEmployeeEmailID()+"'" +" , "+"'"+ employee.getEmployeeAddress()+"'"+" , "+"'"+employee.getEmployeePhone()+"'"  +  " )");
//
//
////			 									st.execute("update Admin set unit="+units+" where id="+rs.getInt(1));
////			 									st.execute("update Admin set bill="+bill+" where id="+rs.getInt(1));
////
//                                            send="Bill_id for current month for customer with id "+id+" is "+rs.getInt(1);
//                                            serverOutputStream.writeObject(send);
////			        	    					System.out.println(units);
////				        	    				E.setUnits(units);
//
//                                        }else {
//                                            send="Not exist";
//                                            serverOutputStream.writeObject(send);
//                                        }
//
////
//                                        y=(String)serverInputStream.readObject();
//                                        if(!y.equals("y"))
//                                            break;
//                                    }
//                                    break;
//                                }
//                                case 2:{
//                                    Vector<String> v = new Vector<String>();
////			       	        		 System.out.println("hii");
//                                    String query="select * from admin order by bill DESC";
//                                    rs=st.executeQuery(query);
//                                    while(rs.next()) {
//                                        query="select * from records where bill_id="+rs.getInt("aid")+" and billpaymentdone=0";
//                                        ResultSet rs5=st1.executeQuery(query);
//                                        if(rs5.next()) {
//                                            String insert="   "+rs5.getInt("c_id")+"            "+rs.getInt("aid")+"            "+rs.getInt("unit")+"           "+rs.getInt("bill")+ "             "+rs5.getString("PayBillDate");
//                                            v.add(insert);
//                                        }
//                                    }
//                                    serverOutputStream.writeObject(v);
//                                    break;
//                                }
//                                case 3:{
//                                    recieve=(String)serverInputStream.readObject();
//                                    int id=Integer.parseInt(recieve);
////	        	    				Employee E=(Employee)hm.get(id);
//                                    rs=st.executeQuery("select * from Customer where id="+id);
//
//                                    if(rs.next()) {
//
//                                        send="Customer Record Deleted Successfully!!\nID= "+rs.getInt("id")+"\nName= "+rs.getString("name")+"\nAddress= "+rs.getString("address");
//                                        serverOutputStream.writeObject(send);
//
//                                        String query="select bill_id from records where c_id="+rs.getInt("id");
//                                        ResultSet rs1=st1.executeQuery(query);
//                                        if(rs1.next()) {
//                                            String query1="delete from admin where aid="+rs1.getInt("bill_id");
//                                            st3.execute(query1);
//                                        }
//                                        query="delete from customer where id="+rs.getInt("id");
//
//                                        st.execute(query);
//
//
//                                    }else {
//                                        send="No customer exist in database for given id sorry can't delete";
//                                        serverOutputStream.writeObject(send);
//                                    }
//                                    break;
//                                }
//                                case 4:{
////			       	        		serverOutputStream.writeObject(hm);
//                                    ArrayList<String> Insert=new ArrayList<>();
//                                    rs=st.executeQuery("select * from Customer");
//                                    while(rs.next()) {
//                                        String insert=""+rs.getInt("id")+"        "+rs.getString("name")+"         "+rs.getString("emailid")+"          "+rs.getString("phone")+"            "+rs.getString("address");
//                                        Insert.add(insert);
//                                    }
//                                    serverOutputStream.writeObject(Insert);
//                                    break;
//                                }
//
//
//                            }
//                            y=(String)serverInputStream.readObject();
//                            if(!y.equals("y"))
//                                break;
//                        }
//                        break;
//                    }
//                    case 3:
//                        String msgin="",msgout="";
//                        Scanner sc=new Scanner(System.in);
//                        while(true) {
//                            msgin=(String)serverInputStream.readObject();
//                            if(!msgin.equals("end")) {
//                                System.out.println("From Client:"+msgin+"\n");
//                                System.out.println("Enter Ans");
//                                msgout=sc.nextLine();
//                                serverOutputStream.writeObject(msgout);
//                            }
//                            else
//                                break;
//
//                        }
//                        break;
//                }
//
//
//                y=(String)serverInputStream.readObject();
//                if(!y.equals("y"))
//                    break;
//            }
//            serverInputStream.close();
//            serverOutputStream.close();
//        }  catch(Exception e) {
//            System.out.println(e);
//        }
//
//
//
//    }
//
//}

