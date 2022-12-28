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
public class flight extends javax.swing.JFrame {

    /**
     * Creates new form flight
     */
    public flight() {
        initComponents();
        getData();
        autoId();
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
                pre = con.prepareStatement("select * from flights");
                ResultSet rs = pre.executeQuery();
                ResultSetMetaData RSMD = rs.getMetaData();
                int cc = RSMD.getColumnCount();
                DefaultTableModel DFT =  (DefaultTableModel) Table.getModel();
                DFT.setRowCount(0);
                while(rs.next()){
                    Vector a = new Vector();
                    for(int i = 0 ; i<=cc ; i++){
                        a.add(rs.getString("FlightID"));
                        a.add(rs.getString("Source"));
                        a.add(rs.getString("Destination"));
                        a.add(rs.getString("Seats"));
                        a.add(rs.getString("Date"));
                    }
                    DFT.addRow(a);
                }
            } catch (SQLException ex) {
                Logger.getLogger(bookTicketPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            pre = con.prepareStatement("select MAX(FlightID) from flights");
            ResultSet rs = pre.executeQuery();
            rs.next();
            if(rs.getString("MAX(FlightID)")==null){
                FlightID.setText("FL001");
            }
            else{
                String s = rs.getString("MAX(FlightID)");
                long n = Long.parseLong(s.substring(2));
                n++;
                FlightID.setText("FL"+String.format("%03d" , n));
            }
        } catch (SQLException ex) {
            Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void insertData(){
       try {
            String flightid = FlightID.getText();
            String source = (String) Source.getSelectedItem();
            String destination = (String) Destination.getSelectedItem();
            String seats = Seats.getText();
            DateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
            // TODO add your handling code here:
            String date = dt.format(Date.getDate());
            Connection con ;
            PreparedStatement pre ;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
            pre = con.prepareStatement("insert into flights(FlightID , Source , Destination , Date , Seats)values(?,?,?,?,?)");
            pre.setString(1 , flightid);
            pre.setString(2 , source);
            pre.setString(3, destination);
            pre.setString(4,date);
            pre.setString(5, seats);
            pre.executeUpdate();
            FlightID.setText("");
            Source.setSelectedItem("select");
            Destination.setSelectedItem("select");
            Seats.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
        }
        getData();   
    }
    public void delete(){
        try {                                         
            // TODO add your handling code here:
            String pass = FlightID.getText();
            Connection con ;
            PreparedStatement pre ;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
            pre = con.prepareStatement("DELETE from flights where FlightID=?");
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

        FlightCode = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Source = new javax.swing.JComboBox<>();
        Destination = new javax.swing.JComboBox<>();
        Date = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        FlightID = new javax.swing.JTextField();
        Seats = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(300, 200));

        FlightCode.setBackground(new java.awt.Color(255, 255, 255));
        FlightCode.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fly High Airlines");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(390, 390, 390))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Flight Code");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Source");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Destination");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Takeof Date");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Number Of Seats");

        Source.setBackground(new java.awt.Color(0, 102, 102));
        Source.setEditable(true);
        Source.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Source.setForeground(new java.awt.Color(255, 255, 255));
        Source.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "Delhi", "Mumbai ", "Chennai", "Kolkata", "Goa" }));
        Source.setBorder(null);

        Destination.setBackground(new java.awt.Color(0, 102, 102));
        Destination.setEditable(true);
        Destination.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Destination.setForeground(new java.awt.Color(255, 255, 255));
        Destination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "Delhi", "Mumbai ", "Chennai", "Kolkata", "Goa" }));
        Destination.setBorder(null);

        Date.setBackground(new java.awt.Color(255, 255, 255));
        Date.setForeground(new java.awt.Color(0, 102, 102));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Edit");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Back");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Manage Flights");

        Table.setAutoCreateRowSorter(true);
        Table.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Table.setForeground(new java.awt.Color(0, 102, 102));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Flight Code", "Source", "Departure", "Seats", "Date"
            }
        ));
        Table.setToolTipText("");
        Table.setSelectionBackground(new java.awt.Color(0, 102, 102));
        Table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Flight List");

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Reset");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        FlightID.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        FlightID.setForeground(new java.awt.Color(0, 102, 102));
        FlightID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Seats.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Seats.setForeground(new java.awt.Color(0, 102, 102));
        Seats.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Seats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeatsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FlightCodeLayout = new javax.swing.GroupLayout(FlightCode);
        FlightCode.setLayout(FlightCodeLayout);
        FlightCodeLayout.setHorizontalGroup(
            FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FlightCodeLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(FlightCodeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FlightID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(Source, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(681, 681, 681))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightCodeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightCodeLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightCodeLayout.createSequentialGroup()
                        .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Destination, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FlightCodeLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel4)))
                        .addGap(79, 79, 79)
                        .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightCodeLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(422, 422, 422))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightCodeLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(387, 387, 387))))
        );
        FlightCodeLayout.setVerticalGroup(
            FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlightCodeLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FlightCodeLayout.createSequentialGroup()
                        .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(20, 20, 20)
                        .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Destination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FlightID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Seats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(FlightCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FlightCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FlightCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.hide();
        mainPanel mp = new mainPanel();
        mp.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertData();
        JOptionPane.showMessageDialog(null, "Flight Added");
        autoId();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        FlightID.setText("");
        Seats.setText("");
        Source.setSelectedItem("select");
        Destination.setSelectedItem("select");
        Table.removeRowSelectionInterval(0, Table.getRowCount()-1);
        autoId();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
            try {                                   
                // TODO add your handling code here:
                int column = 0 ;
                int row = Table.getSelectedRow();
                FlightID.setText(Table.getModel().getValueAt(row , column).toString());
                String pass = FlightID.getText();
                Connection con ;
                PreparedStatement pre ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(flight.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhighairlinedatabase" , "root", "Ankit@1289");
                pre = con.prepareStatement("select * from flights where FlightID=?");
                pre.setString(1,pass);
                ResultSet rs = pre.executeQuery();
                rs.next();
                FlightID.setText(rs.getString("FlightID"));
                Seats.setText(rs.getString("Seats"));
                Source.setSelectedItem(rs.getString("Source"));
                Destination.setSelectedItem(rs.getString("Destination"));
            } catch (SQLException ex) {
                Logger.getLogger(passengers.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_TableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        delete();
        insertData();
        JOptionPane.showMessageDialog(null, "Flight Edited");
        autoId();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        delete();
        JOptionPane.showMessageDialog(null, "Flight Deleted");
        FlightID.setText("");
        Seats.setText("");
        Source.setSelectedItem("select");
        Destination.setSelectedItem("select");    
        getData();
        autoId();
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeatsActionPerformed

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
            java.util.logging.Logger.getLogger(flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(flight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new flight().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date;
    private javax.swing.JComboBox<String> Destination;
    private javax.swing.JPanel FlightCode;
    private javax.swing.JTextField FlightID;
    private javax.swing.JTextField Seats;
    private javax.swing.JComboBox<String> Source;
    private javax.swing.JTable Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
