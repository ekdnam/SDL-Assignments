package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class customerPageAfterLogin extends JFrame implements ActionListener{
    DbCon connection;
    int h, w;
    JLabel mainLabel, usernameLabel, passwordLabel;

    String uname;

    JLabel usernameDetailslLabel, passwordDetailsLabel, nameDetailsLabel, ageDetailsLabel;
    JLabel usernameDetailslData, passwordDetailsData, nameDetailsData, ageDetailsData;
    JTextField tf1;
    JPasswordField pf2;
//    JMenu menu, submenu;
//    JMenuItem loginLogout, customerLoginMenu, adminLoginMenu;
    JButton customerLogout, adminLogin;
    JButton loginButton,clearButton,signupButton, placeOrderButton;
    JButton deleteOrderButton, seeDetailsButton, deleteAccountButton;

    JTextField usernameField;
    JPasswordField passwordField;

    int labelFontSize;
    customerPageAfterLogin(String username){

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        labelFontSize = 15;
        uname = username;

        setSize(900, 500);
        setTitle(username);

        mainLabel= new JLabel("KalpanaLabs");
        mainLabel.setFont(new Font("Osward", Font.BOLD, 24));

        usernameDetailslLabel = new JLabel("Username");
        usernameDetailslLabel.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        passwordDetailsLabel = new JLabel("Password");
        passwordDetailsLabel.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        nameDetailsLabel = new JLabel("Name");
        nameDetailsLabel.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        ageDetailsLabel = new JLabel("Age");
        ageDetailsLabel.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        usernameDetailslData = new JLabel("");
        usernameDetailslData.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        passwordDetailsData = new JLabel("");
        passwordDetailsData.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        nameDetailsData = new JLabel("");
        nameDetailsData.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

        ageDetailsData = new JLabel("");
        ageDetailsData.setFont(new Font("Raleway", Font.BOLD, labelFontSize));

//        menu = new JMenu("");

        customerLogout = new JButton("Logout");
        customerLogout.setFont(new Font("Osward", Font.BOLD, 38));

        adminLogin = new JButton("Admin Login");
        adminLogin.setFont(new Font("Osward", Font.BOLD, 38));

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Ariel",Font.BOLD,15));

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Ariel",Font.BOLD,15));

        tf1 = new JTextField();
        pf2 = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Raleway", Font.BOLD, 14));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setFont(new Font("Osward", Font.BOLD, 38));
//        placeOrderButton.setBackground(Color.BLACK);
//        placeOrderButton.setForeground(Color.WHITE);

        deleteOrderButton = new JButton("Delete Order");
        deleteOrderButton.setFont(new Font("Osward", Font.BOLD, 38));
//        deleteOrderButton.setBackground(Color.BLACK);
//        deleteOrderButton.setForeground(Color.WHITE);

        seeDetailsButton = new JButton("See Details");
        seeDetailsButton.setFont(new Font("Osward", Font.BOLD, 38));
//        seeDetailsButton.setBackground(Color.BLACK);
//        seeDetailsButton.setForeground(Color.WHITE);

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setFont(new Font("Osward", Font.BOLD, 38));
//        deleteAccountButton.setBackground(Color.BLACK);
//        deleteAccountButton.setForeground(Color.WHITE);


        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Raleway", Font.BOLD, 14));
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);

        signupButton = new JButton("Signup!");
        signupButton.setFont(new Font("Raleway", Font.BOLD, 14));
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.WHITE);

        setLayout(null);

        customerLogout.setFont(new Font("Arial", Font.BOLD, 14));
        customerLogout.setBounds(0, 0, 150, 30);
        add(customerLogout);

        adminLogin.setFont(new Font("Arial", Font.BOLD, 14));
        adminLogin.setBounds(0, 30, 150, 30);
        add(adminLogin);

        placeOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        placeOrderButton.setBounds(0, 60, 150, 105);
        add(placeOrderButton);

        deleteOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteOrderButton.setBounds(0, 165, 150, 105);
        add(deleteOrderButton);

        seeDetailsButton.setFont(new Font("Arial", Font.BOLD, 14));
        seeDetailsButton.setBounds(0, 270, 150, 105);
        add(seeDetailsButton);

        deleteAccountButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteAccountButton.setBounds(0, 375, 150, 105);
        add(deleteAccountButton);

        mainLabel.setText(this.uname + "'s details are: ");
        mainLabel.setBounds(200, 60, 800, 50);
        add(mainLabel);
        mainLabel.setVisible(false);

        usernameDetailslLabel.setBounds(200, 110, 100, 50);
        usernameDetailslData.setBounds(200, 140, 100, 50);
        usernameField.setBounds(200, 140, 100, 50);

        add(usernameDetailslLabel);
        add(usernameDetailslData);
        add(usernameField);

        usernameDetailslLabel.setVisible(false);
        usernameDetailslData.setVisible(false);
        usernameField.setVisible(false);

        passwordDetailsLabel.setBounds(300, 110, 100, 50);
        passwordDetailsData.setBounds(300, 140, 100, 50);
        passwordField.setBounds(300, 140, 100, 50);

        add(passwordDetailsLabel);
        add(passwordDetailsData);
        add(passwordField);

        passwordDetailsLabel.setVisible(false);
        passwordDetailsData.setVisible(false);
        passwordField.setVisible(false);

        nameDetailsLabel.setBounds(400, 110, 100, 50);
        nameDetailsData.setBounds(400, 140, 100, 50);

        add(nameDetailsLabel);
        add(nameDetailsData);

        nameDetailsLabel.setVisible(false);
        nameDetailsData.setVisible(false);

        ageDetailsLabel.setBounds(500, 110, 100, 50);
        ageDetailsData.setBounds(500, 140, 100, 50);

        add(ageDetailsLabel);
        add(ageDetailsData);

        ageDetailsLabel.setVisible(false);
        ageDetailsData.setVisible(false);

        customerLogout.addActionListener(this);
        adminLogin.addActionListener(this);

        placeOrderButton.addActionListener(this);
        deleteOrderButton.addActionListener(this);
        seeDetailsButton.addActionListener(this);
        deleteAccountButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) throws Exception {
        String username = "b";
        new customerPageAfterLogin(username).setVisible(true);
    }
    public void placeOrderLayout(){

    }
    public void deleteOrdersLayout(){

    }
    public void seeDetailsLayout(){
        mainLabel.setVisible(true);
        usernameDetailslLabel.setVisible(true);
        passwordDetailsLabel.setVisible(true);
        nameDetailsLabel.setVisible(true);
        ageDetailsLabel.setVisible(true);
    }
    public void deleteAccountLayout(){
        mainLabel.setText("Enter details to delete your account");
        mainLabel.setVisible(true);
        usernameDetailslLabel.setVisible(true);
        passwordDetailsData.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == customerLogout){
            String output = "Session ended";
            setVisible(false);
            JOptionPane.showMessageDialog(this, output);
            new Login().setVisible(true);
        }
        if(ae.getSource() == adminLogin){

        }
        if(ae.getSource() == placeOrderButton){
            placeOrderLayout();
        }
        if(ae.getSource() == deleteOrderButton){
            deleteOrdersLayout();
        }
        if(ae.getSource() == seeDetailsButton){
            seeDetailsLayout();
            DbCon con = new DbCon();
            String[] userDetails = con.showUserDetails(this.uname);

            usernameDetailslData.setText(userDetails[1]);
            passwordDetailsData.setText(userDetails[2]);
            nameDetailsData.setText(userDetails[3]);
            ageDetailsData.setText(userDetails[4]);

            usernameDetailslData.setVisible(true);
            passwordDetailsData.setVisible(true);
            nameDetailsData.setVisible(true);
            ageDetailsData.setVisible(true);

            try{
                con.con.close();
            }
            catch(java.sql.SQLException sqlException){
                System.out.println("error while closing connection");
                sqlException.printStackTrace();
            }
        }
        if(ae.getSource() == deleteAccountButton){
            int input =  JOptionPane.showConfirmDialog(this, "Are you sure?",  "Deletion Authorization", JOptionPane.YES_NO_OPTION);
            if(input == 0) {
//                deleteAccountLayout();
                String password = JOptionPane.showInputDialog(this, "Enter your password");
                DbCon con = new DbCon();
                System.out.println("Connection established");
                String[] userDetails = con.showUserDetails(uname);
                System.out.println("Getting details");
                if (password.equals(userDetails[1])){
                    con.deleteUser(uname);
                    JOptionPane.showMessageDialog(this, "Account successfully deleted. Redirecting you to main page");
                    setVisible(false);
                    new Login().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Password entered is incorrect. Deletion is unauthorized");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Okay, taking you back to customer gateway");
            }
        }
    }
//public void actionPerformed(ActionEvent ae){
//
//		try {
//			String userID= tf1.getText();
//			String pin = pf2.getText();
//
//			if(ae.getSource()==loginButton) {
//				conn c1 = new conn();
//				String q = "Select * from user where id=? and pin=?";
//				PreparedStatement st1 = c1.c.prepareStatement(q);
//				st1.setInt(1,Integer.parseInt(userID));
//				st1.setString(2, pin);
//				ResultSet rs= st1.executeQuery();
//				if(rs.next()) {
//					new UserMenu().setVisible(true);
//					setVisible(false);
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "Incorrect Card Number or Password");
//				}
//			}
//			else if(ae.getSource()==clearButton){
//				tf1.setText("");
//				pf2.setText("");
//			}
//		else if(ae.getSource()==signupButton) {
//				new Signup().setVisible(true);
//				setVisible(false);
//			}
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//	}
}
