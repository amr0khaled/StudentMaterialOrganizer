/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.amr0khaled.student_organizer.panes;

import com.amr0khaled.student_organizer.Open;
import com.amr0khaled.student_organizer.Subject;
import com.amr0khaled.student_organizer.Week;
import com.amr0khaled.student_organizer.controller.SubjectController;
import com.amr0khaled.student_organizer.panes.CreateSubjectForm;
import com.amr0khaled.student_organizer.panes.CreateWeekForm;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rami3
 */
public class SubjectsContainer extends javax.swing.JPanel {

  /**
   * Creates new form SubjectsContainer
   */
  private final JScrollPane scrollpane = new JScrollPane();
  private final JPanel mainPane = new JPanel();
  private final JLayeredPane pane = new JLayeredPane();
  
  private final File path;
  private final Insets insets = new Insets(20, 25, 20, 25);

  public ArrayList<Subject> subjects;
  public ArrayList<Week> weeks;
  private HashMap<Integer, Subject> subjectsMap;
  private Open openType = Open.Lecture;
  private JButton backButton = new JButton();
  public SubjectsContainer(Optional<ArrayList<Subject>> subjects, File path, Open openType) {
    this.openType = openType;
    initComponents();
    // Initialize Back Button
    BufferedImage iconBuf = null;
    try {
      iconBuf = ImageIO.read(new File("icons/arrow-left.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    Image arrow = iconBuf.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    ImageIcon icon = new ImageIcon(arrow);
    backButton.setIcon(icon);
    backButton.setBounds(10, 10, 40, 40);
    backButton.setPreferredSize(new Dimension(40, 40));
    backButton.setBorderPainted(false);
    backButton.setContentAreaFilled(false);
    backButton.setFocusPainted(false);
    backButton.setOpaque(false);
    backButton.addMouseListener(backButtonToCards());
    // Add mainPane to the DEFAULT_LAYER
    mainPane.setLayout(new java.awt.GridBagLayout());
    mainPane.removeAll();
    pane.add(mainPane, JLayeredPane.DEFAULT_LAYER); 
    pane.addComponentListener(new java.awt.event.ComponentAdapter() {
      @Override
      public void componentResized(java.awt.event.ComponentEvent e) {
        mainPane.setBounds(0, 0, pane.getWidth(), pane.getHeight());
      }
    });


    // Add the layered pane (pane) to the container
    add(pane, BorderLayout.CENTER); 
    
    // scrollpane.setLayout(new ScrollPaneLayout());
    scrollpane.setBorder(null);
    
    Dimension dim = new Dimension(1043, 624);
    setMaximumSize(new Dimension(2147483647, 2147483647));
    setPreferredSize(dim);
    setMinimumSize(dim);
    
    mainPane.setMaximumSize(new Dimension(2147483647, 2147483647));
    mainPane.setPreferredSize(dim);
    mainPane.setMinimumSize(dim);
    mainPane.setBounds(0, 0, 1043, 624);
    
    scrollpane.setPreferredSize(dim);
    scrollpane.setMinimumSize(dim);
    scrollpane.getVerticalScrollBar().setUnitIncrement(16);
    
    //add(scrollpane);
    
    path.mkdir();
    this.path = path;
    this.subjectsMap = new HashMap<Integer, Subject>();
    mainPane.setLayout(new java.awt.GridBagLayout());
    if (subjects.isPresent()) {
      this.subjects = subjects.get();
      addCards();
    } else {
      JLabel l = new JLabel("No Subjects \""+openType.toString() + 's'+"\" were added.");
      l.setLayout(new java.awt.CardLayout());
      l.setFont(new java.awt.Font("Poppins", 0, 32));
      
      NewSubjectCard newSubject = new NewSubjectCard();
      newSubject.addPropertyChangeListener(newSubject.NEW, e -> subjectFormListener(e));
      Component[] panels = {l, newSubject};
      for (int i = 0; i < 2; i ++) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.weightx = 1.0;  
        gbc.weighty = 0;
        gbc.insets = new java.awt.Insets(16, 8, 16, 8);
        mainPane.add(
          panels[i]
          , gbc
        );
      }
      pane.revalidate();
      pane.repaint();
    }
    for (Component c : getComponents()) {
        System.out.println("" + c.getClass().getSimpleName() + "@" + c.hashCode());
    }
  }
  private void subjectFormListener(PropertyChangeEvent e) {
    if ((boolean)e.getNewValue()) {
      CreateSubjectForm subjectForm = new CreateSubjectForm();
      subjectForm.setOpen(openType);
      subjectForm.setSubjectPath(path.getPath());
      subjectForm.addPropertyChangeListener(subjectForm.FINISHED, ev -> newWeekCardListener(e, subjectForm.subjectTitle, subjectForm.dir));
      pane.add(backButton, JLayeredPane.PALETTE_LAYER);
      mainPane.removeAll();
      mainPane.add(subjectForm);
      mainPane.revalidate();
      mainPane.repaint();
    }
  }
  private MouseAdapter backButtonToCards() {
    return new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          addCards();
          pane.remove(backButton);
          pane.revalidate();
          pane.repaint();
        }
      }
    };
  }
  private MouseAdapter backButtonToWeeks(String subjectTitle, String location) {
    return new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          addWeeks(subjectTitle, location);
          backButton.addMouseListener(backButtonToCards());
          pane.revalidate();
          pane.repaint();
        }
      }
    };
  }
  public ArrayList<SubjectCard> getSubjectCards() {
    if (subjects.size() > 0) {
      ArrayList<SubjectCard> cards = new ArrayList<>();
      int i = 0;
      for (Subject subject: subjects) {
        subject.setOpen(openType);
        if (subject.exists()) {
          subject.setPath(path.getPath());
          SubjectCard card = new SubjectCard();
          card.setTitle(subject.getTitle());
          card.setSubjectHash(subject.hashCode());
          card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              card.setOpen(!card.getOpen());
            }
          });
          card.addPropertyChangeListener(card.OPEN, (e) -> {
            if ((boolean)e.getNewValue() == card.getOpen()) {
              Subject sub = subjectsMap.get(card.getSubjectHash());
              this.weeks = sub.getWeeks(openType);
              addWeeks(sub.getTitle(), sub.getLocation());
            }
          });
          cards.add(card);
          subjectsMap.put(card.getSubjectHash(), subject);
        }
        i++;
      }
      return cards;
    }
    return null;
  }
  public Subject getSubject(int hash) {
    for (Subject subject: subjects) {
      if (subject.hashCode() == hash) {
        return subject;
      }
    }
    return null;
  }
  public void addCards() {
    ArrayList<SubjectCard> cards = getSubjectCards();
    int total = cards.size();
    mainPane.removeAll();
    if (cards != null) {
      for (int i = 0; i < total; i++) {
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        if ((i /4) == 0) {
          gbc.insets = new Insets(20, 25, 20, 25);
        } else if (i == (total - 1)) {
          gbc.insets = insets;
        }
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        SubjectCard card = cards.get(i);
        gbc.gridx = i % 4;
        gbc.gridy = i / 4;
        mainPane.add(card, gbc);
      }
      NewSubjectCard newSubject = new NewSubjectCard();
      newSubject.addPropertyChangeListener(newSubject.NEW, e -> subjectFormListener(e));
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.gridx = total % 4;
      gbc.gridy = total / 4;
      gbc.weightx = 0;  
      gbc.weighty = 0;
      gbc.insets = new Insets(20, 25, 20, 25);
      mainPane.add(
        newSubject, gbc
      );
      pane.revalidate();
      pane.repaint();
    }
    pane.revalidate();
    pane.repaint();
  }
  public Week getWeek(int hash) {
    for (Week week: weeks) {
      if (week.hashCode() == hash) {
        return week;
      }
    }
    return null;
  }
  public ArrayList<WeekCard> getWeekCards(String subjectTitle, String location) {
    int size = weeks.size();
    if (size > 0) {
      ArrayList<WeekCard> cards = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        Week week = weeks.get(i);
        WeekCard card = new WeekCard();
        card.setTitle(week.getTitle());
        card.setSubject(subjectTitle);
        card.setPath(location);
        card.setOpenType(openType);
        card.setWeek(week);
        card.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            card.setOpen(!card.getOpen());
          }
        });
        card.addPropertyChangeListener(card.OPEN, (e) -> {
          if ((boolean)e.getNewValue() == card.getOpen()) {
            Week _week = card.getWeek();
            _week.open(this, openType);
          }
        });
        
        card.addPropertyChangeListener(card.DELETE, (e) -> {
          if (e.getOldValue() != e.getNewValue()) {
            addWeeks(subjectTitle, location);
          }
        });
        cards.add(card);
      }
      return cards;
    }
    return null;
  }
  private void weekFormListener(PropertyChangeEvent e) {
    if ((boolean) e.getNewValue()) {
      SubjectController.getInstance().refresh();
      DashboardPanel.getInstance().refresh();
      addCards();
      pane.remove(backButton);
    }
  }
  private void newWeekCardListener(PropertyChangeEvent l, String subjectTitle, String location) {
    if ((boolean)l.getNewValue()) {
        mainPane.removeAll();
        CreateWeekForm weekForm = new CreateWeekForm();
        weekForm.setOpen(openType);
        weekForm.setWeekPath(location);
        weekForm.addPropertyChangeListener(weekForm.FINISHED, e -> weekFormListener(e));
        backButton.addMouseListener(backButtonToWeeks(subjectTitle, location));
        mainPane.add(weekForm);
        mainPane.revalidate();
        mainPane.repaint();
      } else {
        addWeeks(subjectTitle, location);
      }
  }
  
  public void addWeeks(String subjectTitle, String location) {
    ArrayList<WeekCard> cards = getWeekCards(subjectTitle, location);
    mainPane.removeAll();
    NewWeekCard newCard = new NewWeekCard();
    newCard.addPropertyChangeListener(newCard.NEW, l -> newWeekCardListener(l, subjectTitle, location));
    if (cards != null) {
        int total = cards.size();
        for (int i = 0; i < total; i++) {
          java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
          gbc.insets = new java.awt.Insets(24, 8, 24, 8);
          gbc.fill = java.awt.GridBagConstraints.BOTH;
          gbc.gridx = i % 4;
          gbc.gridy = i / 4;
          WeekCard card = cards.get(i);
          mainPane.add(card, gbc);
        }
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(24, 8, 24, 8);
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.gridx = total % 4;
        gbc.gridy = total / 4;
        mainPane.add(newCard, gbc);
      } else {
        JLabel l = new JLabel();
        l.setLayout(new java.awt.CardLayout());
        l.setText("No Weeks were added.");
        l.setFont(new java.awt.Font("Poppins", 0, 32));
        Component[] panels = {l, newCard};
        for (int i = 0; i < 2; i ++) {
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.fill = GridBagConstraints.VERTICAL;
          gbc.gridx = 0;
          gbc.gridy = i;
          gbc.weightx = 1.0;
          gbc.weighty = 0;
          gbc.insets = new java.awt.Insets(16, 8, 16, 8);
          mainPane.add(
            panels[i]
            , gbc
          );
        }
        mainPane.revalidate();
        mainPane.repaint();
      }
      pane.remove(backButton);
      pane.add(backButton, JLayeredPane.PALETTE_LAYER);
      pane.revalidate();
      pane.repaint();
    }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setBorder(null);
    setAutoscrolls(true);
    setMinimumSize(new java.awt.Dimension(1043, 624));
    setPreferredSize(new java.awt.Dimension(1043, 624));
    setLayout(new java.awt.BorderLayout());
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
