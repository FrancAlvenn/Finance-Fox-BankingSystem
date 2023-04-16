/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaui_bankingsystem;

import admin.Admin_Data;

public class About extends javax.swing.JFrame implements Admin_Data{


    public About() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setIconImage(brandIconSVG);
        close.setIcon(closeSVG);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableScrollButton1 = new design.TableScrollButton();
        jScrollPaneCustom1 = new design.JScrollPaneCustom();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finance Fox");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPaneCustom1.setBorder(null);

        jTextPane1.setBorder(null);
        jTextPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTextPane1.setText("End User Agreement:\n\nWelcome to Finance Fox, the online banking system that helps you manage your finances from the comfort of your home. By using Finance Fox, you agree to the following terms and conditions:\n\nEligibility: You must be at least 18 years old and a legal resident of the country in which you reside to use Finance Fox. You must also have a valid email address and mobile phone number.\n\nAccount Creation: To use Finance Fox, you must create an account with a unique username and password. You are responsible for maintaining the confidentiality of your username and password.\n\nSecurity: You agree to take all necessary steps to ensure the security of your account, including not sharing your login credentials with anyone else. You are responsible for all actions taken using your account.\n\nUse of Information: Finance Fox may collect personal and financial information from you to provide its services. You agree that Finance Fox may use this information to provide you with its services and to improve its products and services.\n\nFees: Finance Fox may charge fees for its services. These fees will be clearly disclosed to you before you use any service that incurs a fee.\n\nTermination: Finance Fox may terminate your account at any time, for any reason, with or without notice.\n\nIndemnification: You agree to indemnify and hold Finance Fox harmless from any claims, damages, or losses arising from your use of its services.\n\nGoverning Law: This agreement is governed by the laws of the country in which you reside.\n\n\nAll About Us:\n\nWelcome to Finance Fox, the online banking system that provides a friendly space for your transactions. Our mission is to make banking less of a hassle and more of a pleasant experience for you. We strive to be a reliable and efficient banking platform for our users.\n\nOur Vision:\nOur vision is to become an internationally recognized organization that provides friendly, efficient, and reliable banking services to a larger audience. We believe that banking should be accessible to everyone, regardless of their location or financial background.\n\nOur Services:\nFinance Fox offers a range of services to help you manage your finances, including:\n\nDeposit: You can deposit funds into your Finance Fox account through various methods, including bank transfers and credit/debit card payments.\nWithdraw: You can withdraw funds from your Finance Fox account through various methods, including bank transfers and ATM withdrawals.\nTransfer Funds: You can transfer funds between Finance Fox accounts or to other bank accounts.\nPay Bills: You can pay your bills online through Finance Fox.\nTransaction History: You can view your transaction history and account balances at any time through our platform.\nOur Team:\nFinance Fox was created by a single person who made the system from scratch. Franc Alvenn Dela Cruz is a BSIT first-year college student from Dr. Yanga's Colleges, Inc. He has a passion for technology and has been developing software since he was in high school. He built Finance Fox to help people manage their finances in a user-friendly way.\n\nContact Us:\nIf you have any questions or concerns, please don’t hesitate to contact us. You can reach us by email or through our website. We value your feedback and suggestions and are always looking for ways to improve our services. Thank you for choosing Finance Fox for your banking needs.");
        jTextPane1.setPreferredSize(new java.awt.Dimension(600, 400));
        jScrollPaneCustom1.setViewportView(jTextPane1);

        tableScrollButton1.add(jScrollPaneCustom1, java.awt.BorderLayout.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("ABOUT US!");

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(322, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 123, Short.MAX_VALUE)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private design.JScrollPaneCustom jScrollPaneCustom1;
    private javax.swing.JTextPane jTextPane1;
    private design.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
