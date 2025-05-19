/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer;
import com.amr0khaled.student_organizer.Open;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author rami3
 */

public class Week {
  private String path = "";
  private int num = 1;
  private String title = "";
  
  public Week(int num) {
    this.num = num;
    title = "Week "+num;
  }
  private String OpenToString(Open type) {
    switch (type) {
      case Open.Assignment:
        return "assignment";
      case Open.Section:
        return "section";
      default:
        return "lecture";
    }
  }
  public boolean exists(Open open) {
    File file = new File(getLocation(open));
    return file.exists();
  }
  public String getTitle() {
    return title;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getLocation(Open type) {
    String where = OpenToString(type) + ".pdf";
    if (this.path.charAt(path.length() - 1) == '/') {
      return this.path + where;
    }
    return this.path + "/" + where;
  }
  public void open(Component com, Open type) {
    String location = getLocation(type);
    File file = new File(location);
    if (Desktop.isDesktopSupported()) {
      Desktop desk = Desktop.getDesktop();
      if (desk.isSupported(Desktop.Action.OPEN)) {
        try {
          desk.open(file);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(
            com,
            "Failed to open file: " + ex.getMessage(),
            "Failure",
            JOptionPane.ERROR_MESSAGE);
        }
      } else {
          JOptionPane.showMessageDialog(
            com,
            "Failed to open file: " + "OPEN action not supported",
            "Failure",
            JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(
        com,
        "Failed to open file: " + "Desktop not supported",
        "Failure",
        JOptionPane.ERROR_MESSAGE);
    }
}
}
