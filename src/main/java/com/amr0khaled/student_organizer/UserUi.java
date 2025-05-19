/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.amr0khaled.student_organizer;
import com.amr0khaled.student_organizer.custom_ui.*;
/**
 *
 * @author rami3
 */
public class UserUi extends javax.swing.JFrame {

  public UserUi() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    jDesktop = new javax.swing.JLayeredPane();
    
    jLabel1 = new javax.swing.JLabel();
    loginFieldUserName = new LoginField();
    loginFieldUserName.setLabelText("User Name");
    
    loginFieldPassword = new LoginField();
    loginFieldPassword.setLabelText("Password");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jDesktop.setBackground(Colors.background);

    jLabel1.setFont(new java.awt.Font("Poppins", 0, 48));
    jLabel1.setForeground(new java.awt.Color(10, 9, 8));
    jLabel1.setText("Log In");
    jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    jDesktop.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktop.setLayer(loginFieldUserName, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
    jDesktop.setLayout(jDesktopLayout);
    jDesktopLayout.setHorizontalGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopLayout.createSequentialGroup()
        .addContainerGap(572, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(556, 556, 556))
      .addGroup(jDesktopLayout.createSequentialGroup()
        .addGap(450, 450, 450)
        .addComponent(loginFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jDesktopLayout.setVerticalGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jDesktopLayout.createSequentialGroup()
        .addGap(115, 115, 115)
        .addComponent(jLabel1)
        .addGap(47, 47, 47)
        .addComponent(loginFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(398, Short.MAX_VALUE))
    );
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jDesktop, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jDesktop, javax.swing.GroupLayout.Alignment.TRAILING)
    );

    pack();
  }// </editor-fold>                        

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
      java.util.logging.Logger.getLogger(UserUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(UserUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(UserUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(UserUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new UserUi().setVisible(true);
      }
    });
  }

  private javax.swing.JLayeredPane jDesktop;
  private javax.swing.JLabel jLabel1;
  private LoginField loginFieldUserName;
  private LoginField loginFieldPassword;
}
