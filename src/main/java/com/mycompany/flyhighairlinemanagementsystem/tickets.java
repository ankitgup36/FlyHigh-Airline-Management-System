/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flyhighairlinemanagementsystem;

import com.mycompany.airlineprojectmanagementsystem.bookTicketPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ankit
 */
public class tickets extends javax.swing.JFrame {

    /**
     * Creates new form passengers
     */
    public tickets() {
        initComponents();
        autoId();
        getData();
        setPassMenu();
        setFlightMenu();
    }
    public void autoId(){
        try {
            Connection con ;
            PreparedStatement pre ;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root" , "Ankit@1289");
            pre = con.prepareStatement("select MAX(TicketID) from tickets");
            ResultSet rs = pre.executeQuery();
            rs.next();
            if(rs.getString("MAX(TicketID)")==null){
                TicketID.setText("TC001");
            }
            else{
                String s = rs.getString("MAX(TicketID)");
                long n = Long.parseLong(s.substring(2));
                n++;
                TicketID.setText("TC"+String.format("%03d" , n));
            }
        } catch (SQLException ex) {
            Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void getData(){
try {                                         
                // TODO add your handling code here:
                Connection con ;
                PreparedStatement pre = null ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(com.mycompany.airlineprojectmanagementsystem.LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root" , "Ankit@1289");
                pre = con.prepareStatement("select * from tickets");
                ResultSet rs = pre.executeQuery();
                ResultSetMetaData RSMD = rs.getMetaData();
                int cc = RSMD.getColumnCount();
                DefaultTableModel DFT =  (DefaultTableModel) Table.getModel();
                DFT.setRowCount(0);
                while(rs.next()){
                    Vector a = new Vector();
                    for(int i = 0 ; i<=cc ; i++){
                        a.add(rs.getString("TicketID"));
                        a.add(rs.getString("Name"));
                        a.add(rs.getString("FlightID"));
                        a.add(rs.getString("Gender"));
                        a.add(rs.getString("Passport"));
                        a.add(rs.getString("Amount"));
                        a.add(rs.getString("Contact"));
                        a.add(rs.getString("Nation"));
                    }
                    DFT.addRow(a);
                }
            } catch (SQLException ex) {
                Logger.getLogger(bookTicketPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void setPassMenu(){
            try {
                Connection con ;
                PreparedStatement pre ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root" , "Ankit@1289");
                pre = con.prepareStatement("select CustomerID from customers");
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                    PassengerID.addItem(rs.getString("CustomerID"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(tickets.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void setFlightMenu(){
            try {
                Connection con ;
                PreparedStatement pre ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root" , "Ankit@1289");
                pre = con.prepareStatement("select FlightID from flights");
                ResultSet rs = pre.executeQuery();
                while(rs.next()){
                    FlightCode.addItem(rs.getString("FlightID"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(tickets.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void insertData(){
 try {
            String ticketid = TicketID.getText();
            String gender = (String) Gender.getSelectedItem();
            String nation = (String) Nation.getSelectedItem();
            String name = Name.getText();
            String flightcode = (String) FlightCode.getSelectedItem();
            String passport = Passport.getText();
            String amount = Amount.getText();
            String contact = Contact.getText();
            String passid = (String) PassengerID.getSelectedItem();
            Connection con ;
            PreparedStatement pre ;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
            pre = con.prepareStatement("insert into tickets(TicketID , FlightID ,Name , Gender , Contact , Amount , Passport , Nation,PassengerID)values(?,?,?,?,?,?,?,?,?)");
            
            pre.setString(1 , ticketid);
            pre.setString(2 , flightcode);
            pre.setString(3, name);
            pre.setString(4,gender);
            pre.setString(5, contact);
            pre.setString(6, amount);
            pre.setString(7, passport);
            pre.setString(8, nation);
            pre.setString(9,passid);
            pre.executeUpdate();
            FlightCode.setSelectedItem("select");
            PassengerID.setSelectedItem("select");
            Gender.setSelectedItem("select");
            Nation.setSelectedItem("select");
            TicketID.setText("");
            Name.setText("");
            Contact.setText("");
            Amount.setText("");
            Passport.setText("");
             autoId();
             getData();
        } catch (SQLException ex) {
            Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(){
        try {                                         
            // TODO add your handling code here:
            String pass = TicketID.getText();
            Connection con ;
            PreparedStatement pre ;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
            pre = con.prepareStatement("DELETE from tickets where TicketID=?");
            pre.setString(1,pass);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(passengers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Passport = new javax.swing.JTextField();
        Amount = new javax.swing.JTextField();
        Contact = new javax.swing.JTextField();
        Gender = new javax.swing.JComboBox<>();
        Nation = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        TicketID = new javax.swing.JTextField();
        FlightCode = new javax.swing.JComboBox<>();
        PassengerID = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();
        edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(150, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fly High Airlines");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Bookings");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Passenger ID");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Flight Code");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Passport No");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Amount");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Nationality");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Contact");

        Name.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Name.setForeground(new java.awt.Color(0, 102, 102));
        Name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });

        Passport.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Passport.setForeground(new java.awt.Color(0, 102, 102));
        Passport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Amount.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Amount.setForeground(new java.awt.Color(0, 102, 102));
        Amount.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Contact.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Contact.setForeground(new java.awt.Color(0, 102, 102));
        Contact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactActionPerformed(evt);
            }
        });

        Gender.setBackground(new java.awt.Color(0, 102, 102));
        Gender.setEditable(true);
        Gender.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Gender.setForeground(new java.awt.Color(255, 255, 255));
        Gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "Male", "Female", "Others" }));
        Gender.setBorder(null);

        Nation.setBackground(new java.awt.Color(0, 102, 102));
        Nation.setEditable(true);
        Nation.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Nation.setForeground(new java.awt.Color(255, 255, 255));
        Nation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "India", "Pakistan", "America", "Japan", "Bangladesh", "Nepal", "Sri Lanka" }));
        Nation.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setText("Book Tickets");

        Table.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Table.setForeground(new java.awt.Color(0, 102, 102));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket ID", "Name", "Flight Code", "Gender", "Passport Number", "Amount", "Contact", "Nationality"
            }
        ));
        Table.setSelectionBackground(new java.awt.Color(0, 102, 102));
        Table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Book");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Back");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("Ticket No");

        TicketID.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        TicketID.setForeground(new java.awt.Color(0, 102, 102));
        TicketID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        FlightCode.setBackground(new java.awt.Color(0, 102, 102));
        FlightCode.setEditable(true);
        FlightCode.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        FlightCode.setForeground(new java.awt.Color(255, 255, 255));
        FlightCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        FlightCode.setBorder(null);

        PassengerID.setBackground(new java.awt.Color(0, 102, 102));
        PassengerID.setEditable(true);
        PassengerID.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        PassengerID.setForeground(new java.awt.Color(255, 255, 255));
        PassengerID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        PassengerID.setBorder(null);
        PassengerID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PassengerIDItemStateChanged(evt);
            }
        });

        delete.setBackground(new java.awt.Color(0, 102, 102));
        delete.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.setBorder(null);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(0, 102, 102));
        edit.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Edit");
        edit.setBorder(null);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PassengerID, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(FlightCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Passport, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Nation, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Contact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(31, 31, 31)
                                .addComponent(TicketID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(295, 295, 295)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(36, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(516, 516, 516))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(TicketID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Passport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlightCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PassengerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(edit)
                    .addComponent(delete))
                .addGap(63, 63, 63)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        mainPanel mp = new mainPanel();
        mp.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        insertData();
        JOptionPane.showMessageDialog(null, "Ticket Added");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void PassengerIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PassengerIDItemStateChanged
        // TODO add your handling code here:
            try {
                String pass = (String) PassengerID.getSelectedItem();
                Connection con ;
                PreparedStatement pre ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root" , "Ankit@1289");
                pre = con.prepareStatement("select * from customers where CustomerID=?");
                pre.setString(1, pass);
                ResultSet rs = pre.executeQuery();
                rs.next();
                Name.setText(rs.getString("Name"));
                Passport.setText(rs.getString("Passport"));
                Contact.setText(rs.getString("Contact"));
                Gender.setSelectedItem(rs.getString("Gender"));
                Nation.setSelectedItem(rs.getString("Nation"));
            } catch (SQLException ex) {
                Logger.getLogger(tickets.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_PassengerIDItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            Name.setText("");
            Passport.setText("");
            Contact.setText("");
            Gender.setSelectedItem("select");
            Nation.setSelectedItem("select");
            PassengerID.setSelectedItem("select");
            FlightCode.setSelectedItem("select");
            Table.removeRowSelectionInterval(0, Table.getRowCount()-1);
            autoId();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        delete();
        JOptionPane.showMessageDialog(null, "Ticket Deleted");
            Name.setText("");
            Passport.setText("");
            Contact.setText("");
            Gender.setSelectedItem("select");
            Nation.setSelectedItem("select");
            PassengerID.setSelectedItem("select");
            FlightCode.setSelectedItem("select");  
            
        getData();
        autoId();
        
    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        delete();
        insertData();
        JOptionPane.showMessageDialog(null, "Passenger Updated");
    }//GEN-LAST:event_editActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
                try {                                   
                    // TODO add your handling code here:
                    int column = 0 ;
                    int row = Table.getSelectedRow();
                    TicketID.setText(Table.getModel().getValueAt(row , column).toString());
                    String pass = TicketID.getText();
                    Connection con ;
                    PreparedStatement pre ;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
                    pre = con.prepareStatement("select * from tickets where TicketID=?");
                    pre.setString(1,pass);
                    ResultSet rs = pre.executeQuery();
                    rs.next();
                    TicketID.setText(rs.getString("TicketID"));
                    FlightCode.setSelectedItem(rs.getString("FlightID"));
                    TicketID.setText(rs.getString("TicketID"));
                    PassengerID.setSelectedItem(rs.getString("PassengerID"));
                    Name.setText(rs.getString("Name"));
                    Gender.setSelectedItem(rs.getString("Gender"));
                    Nation.setSelectedItem(rs.getString("Nation"));
                    Contact.setText(rs.getString("Contact"));
                    Passport.setText(rs.getString("Passport"));
                    Amount.setText(rs.getString("Amount"));
                } catch (SQLException ex) {
                    Logger.getLogger(tickets.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_TableMouseClicked

    private void ContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContactActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tickets().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Amount;
    private javax.swing.JTextField Contact;
    private javax.swing.JComboBox<String> FlightCode;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JTextField Name;
    private javax.swing.JComboBox<String> Nation;
    private javax.swing.JComboBox<String> PassengerID;
    private javax.swing.JTextField Passport;
    private javax.swing.JTable Table;
    private javax.swing.JTextField TicketID;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
