/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaui_bankingsystem;

import admin.Admin_Data;
import design.GlassPanePopup;
import static javaui_bankingsystem.MainFrame.*;
import java.sql.*;
import static javaui_bankingsystem.Message.msgbody;
import static javaui_bankingsystem.Message.msgtitle;

/**
 *
 * @author Administrator
 */
public class Withdraw extends javax.swing.JFrame implements Admin_Data{

    public Withdraw() {
        initComponents();
        GlassPanePopup.install(this);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setIconImage(brandIconSVG);
        close.setIcon(closeSVG);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        withdrawAmount = new javax.swing.JTextField();
        close = new javax.swing.JLabel();
        customButton1 = new design.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Finance Fox");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 410));

        jLabel6.setBackground(new java.awt.Color(0, 153, 255));
        jLabel6.setOpaque(true);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Note: \n        The maximum amount allowed to be withdrawn is : above 100 and below 10,000 pesos. \n    Thank you for understanding!\n\n                                                                                                                             -management");
        jTextArea1.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("WITHDRAW");

        jLabel3.setText("Amount to be withdrawn");

        jLabel5.setBackground(new java.awt.Color(0, 153, 255));
        jLabel5.setOpaque(true);

        withdrawAmount.setBorder(null);

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        customButton1.setBackground(new java.awt.Color(0, 153, 255));
        customButton1.setForeground(new java.awt.Color(255, 255, 255));
        customButton1.setText("Confirm");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(withdrawAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(withdrawAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        MainFrame mf = new MainFrame();
        mf.show();
        dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
       if (Double.parseDouble(withdrawAmount.getText()) < 10000){
            try {
                //accesses the balance from the database
                Connection connection = DriverManager.getConnection(database, username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT Balance FROM bank_user WHERE Account_Number = ?");
                statement.setString(1, value);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                currentValue = resultSet.getDouble("Balance");

                //calculates new balance
                balance = currentValue - Double.valueOf(withdrawAmount.getText());

                //updates the database
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE bank_user SET Balance = ? WHERE Account_Number = ?");
                updateStatement.setDouble(1, balance);
                updateStatement.setString(2, value);
                updateStatement.executeUpdate();

                String sql = "INSERT INTO `" + value + "` (`DESCRIPTION`, `AMOUNT`, `BALANCE`) " +
                "VALUES ('WITHDRAW', " + withdrawAmount.getText() + ", " + balance + ")";

                sqlConn = DriverManager.getConnection(MainFrame.database,username,password);
                pst = sqlConn.prepareStatement("select * from " + value);
                pst.executeUpdate(sql);
                updateDB();
                // Close the database connection
                connection.close();

                MainFrame.getBalance();
                disBalance.setText(currentValue.toString());
                
                //Confirmation Message
                msgtitle = "TRANSACTION COMPLETE!";
                msgbody = ("You have withdrawn an amount of: ₱ " + withdrawAmount.getText() + " from your account! Your current balance is: ₱ "
                        + currentValue +"! \nThank you for using Finance Fox Banking!");
                GlassPanePopup.showPopup(new Message());
            
                withdrawAmount.setText("");
                


            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
            }
        }else{
            msgtitle = "NOTICE!";
            msgbody = ("The amount exceeds the withdraw limit");
            GlassPanePopup.showPopup(new Message());
        }
    }//GEN-LAST:event_customButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Withdraw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Withdraw().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private design.CustomButton customButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField withdrawAmount;
    // End of variables declaration//GEN-END:variables
}
