/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer.custom_ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author rami3
 */
public class RoundedBorder extends AbstractBorder {
  private Color color;
  private int radius;
  private int thickness;
  
  public RoundedBorder(Color color, int radius, int thickness) {
    this.color = color;
    this.radius = radius;
    this.thickness = thickness;
  }
  @Override
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(color);
    g2.setStroke(new BasicStroke(thickness));
    int of = thickness / 2;
    g2.drawRoundRect(x + of, y + of, width - thickness, height - thickness, radius, radius);
    g2.dispose();
  }
  @Override
  public Insets getBorderInsets(Component c, Insets insets) {
    insets.set(thickness, thickness, thickness, thickness);
    return insets;
  }
}
