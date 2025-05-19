/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.amr0khaled.student_organizer.panes;
import com.amr0khaled.student_organizer.Open;
import com.amr0khaled.student_organizer.Week;
import com.amr0khaled.student_organizer.custom_ui.Colors;
import com.amr0khaled.student_organizer.custom_ui.RoundedBorder;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author rami3
 */
public class WeekCard extends javax.swing.JPanel {

  /**
   * Creates new form WeekCard
   */
  private PropertyChangeSupport pcs;
  private String subject = "SUBJECT";
  private Week week;
  private String path;
  public String OPEN = "open_week";
  public String DELETE = "delete_week";
  public boolean deleted = false;
  public boolean open = false;
  private Open openType;
  private JPopupMenu popmenu;
  public WeekCard() {
    pcs = new PropertyChangeSupport(this);
    initComponents();
    initPopMenu();
  }
  
  private void initPopMenu() {
    popmenu = new JPopupMenu("week_card");
    popmenu.setMinimumSize(new Dimension(150, 40 * 3));
    popmenu.setPreferredSize(new Dimension(150, 40 * 3));
    
    JMenuItem details = new JMenuItem("Details", JMenuItem.CENTER);
    details.setPreferredSize(new Dimension(150, 40));
    details.setFont(new Font("Poppins Light", 0, 16));
    JMenuItem replace = new JMenuItem("Replace File", JMenuItem.CENTER);
    replace.setPreferredSize(new Dimension(150, 40));
    replace.setFont(new Font("Poppins Light", 0, 16));
    JMenuItem remove = new JMenuItem("Delete", JMenuItem.CENTER);
    remove.setPreferredSize(new Dimension(150, 40));
    remove.setFont(new Font("Poppins Light", 0, 16));
    
    
    details.addActionListener(e -> {
      String location = getFileLocation();
      JOptionPane.showMessageDialog(this, "File location: " + location, "Details", JOptionPane.PLAIN_MESSAGE);
    });
    replace.addActionListener(e -> {
      JFileChooser dialog = new JFileChooser(getFileLocation());
      int state = dialog.showOpenDialog(null);
      if (state == JFileChooser.APPROVE_OPTION) {
        File file = dialog.getSelectedFile();
        Path filePath = Paths.get(file.getPath());
        Path destPath = Path.of(week.getLocation(openType));
        try {
          Files.copy(filePath, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Error: "+ ex.getMessage(), "Failure in replacing file", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    
    remove.addActionListener(e -> {
      int ans = JOptionPane.showConfirmDialog(
        this,
        "Do you really want to remove this file ?",
        "Remove " + getTitle(),
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
      );
      switch (ans) {
        case JOptionPane.NO_OPTION:
          System.out.println("cancel " + getTitle());
          break;
          
        case JOptionPane.OK_OPTION:
        {
          Path path = Paths.get(week.getLocation(openType));
          try {
            setDeleted(true);
            Files.delete(path);
          } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ ex.getMessage(), "Failure in Deleting", JOptionPane.ERROR_MESSAGE);
          }
          break;
        }

      }
    });
    popmenu.add(details);
    popmenu.add(replace);
    popmenu.add(remove);
    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        showPopmenu(e);
      }
      public void showPopmenu(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popmenu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
    });
  }
  public void setSubject(String title) {
    this.subject = title;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public void setOpenType(Open open) {
    this.openType = open;
  }
  public String getFileLocation() {
    return week.getLocation(openType);
  }
  public int getWeekHash() {
    return week.hashCode();
  }
  public String getTitle() {
    return title.getText();
  }
  public void setTitle(String title) {
    this.title.setText(title);
  }
  public Week getWeek() {
    return week;
  }
  public void setWeek(Week week) {
    this.week = week;
    setTitle(week.getTitle());
  }
  public boolean getOpen() {
    return this.open;
  }
  public void setOpen(boolean open) {
    boolean old = this.open;
    this.open = open;
    pcs.firePropertyChange(OPEN, old, open);
  }
  
  public boolean getDeleted() {
    return this.open;
  }
  public void setDeleted(boolean value) {
    boolean old = this.deleted;
    this.deleted = value;
    pcs.firePropertyChange(DELETE, old, value);
  }
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(name, listener);
  }
  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    pcs.removePropertyChangeListener(name, listener);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    title = new javax.swing.JLabel();

    setBorder(new RoundedBorder(Colors.divider, 16, 1));
    setMaximumSize(new java.awt.Dimension(180, 70));
    setMinimumSize(new java.awt.Dimension(180, 70));
    setName(""); // NOI18N
    setPreferredSize(new java.awt.Dimension(180, 70));
    setLayout(new java.awt.GridBagLayout());

    title.setFont(new java.awt.Font("Poppins ExtraLight", 0, 24)); // NOI18N
    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    title.setText("WEEK");
    add(title, new java.awt.GridBagConstraints());
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel title;
  // End of variables declaration//GEN-END:variables
}
