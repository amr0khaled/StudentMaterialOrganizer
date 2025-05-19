/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer.panes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author rami3
 */
public class NewWeekCard extends WeekCard {
  private PropertyChangeSupport pcs;
  public String NEW = "new_week";
  private boolean New = false;
  public NewWeekCard() {
    pcs = new PropertyChangeSupport(this);

    this.setTitle("+");
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          setNew(!New);
        }
      }
    });
  }
  public boolean getNew() {
    return this.New;
  }
  public void setNew(boolean value) {
    boolean old = this.New;
    this.New = value;
    pcs.firePropertyChange(NEW, old, value);
  }
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(name, listener);
  }
  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    pcs.removePropertyChangeListener(name, listener);
  }
}
