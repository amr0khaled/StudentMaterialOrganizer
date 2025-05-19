/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer.custom_ui;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import javax.swing.JPasswordField;

/**
 *
 * @author rami3
 */
public class RoundedPasswordField extends JPasswordField implements Serializable {
  private Shape shape;
  public RoundedPasswordField(int size) {
    super(size);
    setOpaque(false);
  }
  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    super.paintComponent(g);
  }
  @Override
  protected void paintBorder(Graphics g) {
    g.setColor(getForeground());
    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
  }
  public boolean contains(int x, int y) {
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new RoundRectangle2D.Float(0, 0, getWidth() -1, getHeight() - 1, 15, 15);
    }
    return shape.contains(x, y);
  }
}