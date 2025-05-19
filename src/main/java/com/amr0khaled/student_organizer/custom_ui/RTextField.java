/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package com.amr0khaled.student_organizer.custom_ui;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author rami3
 */
public class RTextField extends RoundedTextField implements Serializable {
  private PropertyChangeSupport propertySupport;
  
  public RTextField(int size) {
    super(size);
    propertySupport = new PropertyChangeSupport(this);
  }
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertySupport.addPropertyChangeListener(listener);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    propertySupport.removePropertyChangeListener(listener);
  }
  
}
