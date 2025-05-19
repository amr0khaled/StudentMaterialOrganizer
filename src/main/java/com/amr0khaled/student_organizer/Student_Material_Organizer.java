/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.amr0khaled.student_organizer;

import com.amr0khaled.student_organizer.controller.LoginController;
import com.amr0khaled.student_organizer.controller.SubjectController;

/**
 *
 * @author amr0khaled0x
 */
public class Student_Material_Organizer {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    LoginController auth = new LoginController();
    SubjectController.getInstance().setRepo("/home/rami3/.cache/student_materials/");
    java.awt.EventQueue.invokeLater(() -> {
      Framer framer = new Framer(auth);
      framer.setVisible(true);
    });
    
    System.out.println("END!");
  }
}
