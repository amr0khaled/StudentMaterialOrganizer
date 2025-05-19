
package com.amr0khaled.student_organizer.custom_ui;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.io.Serializable;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.LineBorder;


/**
 * A JavaBean Swing component that displays a label and a rounded text field
 * with an inner shadow effect.<br>
 * Renamed as LoginField for custom usage.
 */
public class LoginField extends JComponent implements Serializable {
    private static final long serialVersionUID = 1L;

    // JavaBean properties
    private String labelText = "Label";
    private int cornerRadius = 15;
    private Color backgroundColor = Colors.background;
    private Color foregroundColor = Colors.text;
    private Color shadowColor = new Color(0, 0, 0, 20); // inner shadow color
    private int shadowSize = 2; // width of inner shadow border

    // Swing subcomponents
    private final JLabel label;
    private final JTextField textField;

    // Property change support
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Default constructor for JavaBean compliance.
     */
    public LoginField() {
        setLayout(null);
        setOpaque(false);

        // Label setup
        label = new JLabel(labelText);
        label.setFont(new Font("Poppins", Font.PLAIN, 24));
        label.setForeground(foregroundColor);
        add(label);

        textField = new JTextField();
        
        textField.setOpaque(true);
        textField.setLayout(null);
        textField.setPreferredSize(new Dimension(508, 48));
        textField.setFont(new Font("Poppins", Font.PLAIN, 20));
        textField.setForeground(foregroundColor);
        textField.setBackground(backgroundColor);
        Color bg = foregroundColor;
        System.out.println();
        Color borderColor = new Color(
          bg.getRed(),
          bg.getGreen(),
          bg.getBlue(),
          (int) (0.2 * 255)
        );
        textField.setBorder(new LineBorder(borderColor, 1, true));
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);
        add(textField);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
          .addContainerGap()
          .addGap(556, 556, 556))
          .addComponent(label)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
          .addContainerGap()
          .addGap(8))
          .addComponent(textField)
        );
        // Fire property change when text changes
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                pcs.firePropertyChange("text", null, getText());
            }
        });
    }

    // ----- JavaBean getters/setters -----

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        String old = this.labelText;
        this.labelText = labelText;
        label.setText(labelText);
        pcs.firePropertyChange("labelText", old, labelText);
        revalidate();
        repaint();
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        String old = getText();
        textField.setText(text);
        pcs.firePropertyChange("text", old, text);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        int old = this.cornerRadius;
        this.cornerRadius = cornerRadius;
        pcs.firePropertyChange("cornerRadius", old, cornerRadius);
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        Color old = this.backgroundColor;
        this.backgroundColor = backgroundColor;
        pcs.firePropertyChange("backgroundColor", old, backgroundColor);
        repaint();
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        Color old = this.foregroundColor;
        this.backgroundColor = foregroundColor;
        pcs.firePropertyChange("foregroundColor", old, foregroundColor);
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        Color old = this.shadowColor;
        this.shadowColor = shadowColor;
        pcs.firePropertyChange("shadowColor", old, shadowColor);
        repaint();
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        int old = this.shadowSize;
        this.shadowSize = shadowSize;
        pcs.firePropertyChange("shadowSize", old, shadowSize);
        repaint();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    // ----- Painting and layout -----

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int labelH = label.getHeight();

        // Define main rounded rect area
        RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
                0, labelH,
                w, h - labelH,
                cornerRadius, cornerRadius
        );

        // Fill background
        g2.setColor(backgroundColor);
        g2.fill(rect);

        g2.dispose();
    }

    @Override
    public void doLayout() {
        int w = getWidth();
        int h = getHeight();
        int labelH = label.getPreferredSize().height;
        label.setBounds(0, 0, w, labelH);

        int inset = cornerRadius / 4;
        
        textField.setBounds(
                0,
                labelH + inset - 2,
                w,
                h - inset - labelH
        );
//        textField.setBounds(
//                inset *3,
//                labelH + inset,
//                w - inset * 4,
//                h - labelH - inset * 2
//        );
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 60);
    }
}

