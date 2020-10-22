package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
    DbCon connection;
    int h, w;
    JLabel mainLabel, usernameLabel, passwordLabel, nameLabel, ageLabel;
//    JLabel mainLabel,usernameLabel,passwordLabel;
    JTextField tf1;
    JPasswordField pf2;
    JTextField nameField, ageField;
    JMenu menu, submenu;
    JMenuItem loginLogout, customerLoginMenu, adminLoginMenu;
    JButton customerLogin, adminLogin;
    JButton loginButton,clearButton,signupButton;

    Login(){
        setSize(300, 600);
        setTitle("KalpanaLabs");
        mainLabel= new JLabel("KalpanaLabs");
        mainLabel.setFont(new Font("Osward", Font.BOLD, 24));

        menu = new JMenu("");

//        loginLogout  = new JMenuItem("Utilities");
//        loginLogout.setFont(new Font("Osward", Font.BOLD, 38));
//
//        customerLoginMenu = new JMenuItem("Customer Login");
//        customerLoginMenu.setFont(new Font("Osward", Font.BOLD, 38));
//
//        adminLoginMenu = new JMenuItem("Admin Login");
//        adminLoginMenu.setFont(new Font("Osward", Font.BOLD, 38));
//
        customerLogin = new JButton("Customer Login");
        customerLogin.setFont(new Font("Osward", Font.BOLD, 38));

        adminLogin = new JButton("Admin Login");
        adminLogin.setFont(new Font("Osward", Font.BOLD, 38));

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Ariel",Font.BOLD,15));

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Ariel",Font.BOLD,15));

        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Ariel",Font.BOLD,15));

        ageLabel = new JLabel("Age");
        ageLabel.setFont(new Font("Ariel",Font.BOLD,15));

        tf1 = new JTextField();
        pf2 = new JPasswordField();
        nameField = new JTextField();
        ageField = new JTextField();

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Raleway", Font.BOLD, 14));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Raleway", Font.BOLD, 14));
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);

        signupButton = new JButton("Signup!");
        signupButton.setFont(new Font("Raleway", Font.BOLD, 14));
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.WHITE);

        setLayout(null);

        customerLogin.setFont(new Font("Arial", Font.BOLD, 14));
        customerLogin.setBounds(0, 0, 150, 30);
        add(customerLogin);

        adminLogin.setFont(new Font("Arial", Font.BOLD, 14));
        adminLogin.setBounds(150, 0, 150, 30);
        add(adminLogin);

        mainLabel.setBounds(65,10,150,200);
        add(mainLabel);

        usernameLabel.setBounds(10,80,100,200);
        add(usernameLabel);

        passwordLabel.setBounds(10,120,100,200);
        add(passwordLabel);

        nameLabel.setBounds(10,160,100,200);
        add(nameLabel);

        ageLabel.setBounds(10,200,100,200);
        add(ageLabel);

        tf1.setBounds(110,170,150,25);
        tf1.setFont(new Font("Arial", Font.BOLD, 19));
        add(tf1);

        pf2.setFont(new Font("Arial", Font.BOLD, 19));
        pf2.setBounds(110,210,150,25);
        add(pf2);

        nameField.setFont(new Font("Arial", Font.BOLD, 19));
        nameField.setBounds(110,250,150,25);
        add(nameField);

        ageField.setFont(new Font("Arial", Font.BOLD, 19));
        ageField.setBounds(110,290,150,25);
        add(ageField);

        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBounds(110,350,100,30);
        add(signupButton);

        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBounds(110,380,100,30);
        add(clearButton);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        signupButton.addActionListener(this);

        //getContentPane().setBackground(Color.WHITE);
        //setSize(650,750);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        //setLocation(500,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) throws Exception {
        new Login().setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String username = tf1.getText();
        String password = pf2.getText();
        String name = nameField.getText();
        String age = ageField.getText();

        if(ae.getSource() == signupButton){
            DbCon connection = new DbCon();

            tf1.setText("");
            pf2.setText("");
            nameField.setText("");
            ageField.setText("");

            String output = connection.insertUser(username, password, name, age);
            JOptionPane.showMessageDialog(this, output);
            try {
                connection.con.close();
                if(output.equals("User added! Welcome to KalpanaLabs!\nRemember your password :)")) {
                    setVisible(false);
                    new customerPageAfterLogin(username).setVisible(true);
                }
            }
            catch(java.sql.SQLException sqlException){
                System.out.println("An error occurred while closing the connection");
                sqlException.printStackTrace();
            }
        }
        if(ae.getSource() == clearButton){
            tf1.setText("");
            pf2.setText("");
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
