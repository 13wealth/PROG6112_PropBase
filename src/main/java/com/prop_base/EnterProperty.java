package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class EnterProperty extends JPanel 
{
    private final int cornerRadius = 20;
    private final int borderThickness = 0;
    private final Color borderColor = new Color(200, 200, 200);
    private final Color bgColor = Color.WHITE;

    private final java.util.Map<String, JComponent> components = new java.util.HashMap<>();

    public EnterProperty() 
    {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setPreferredSize(new Dimension(900, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int row = 0;

        // Title
        JLabel title = new JLabel("Add New Property", JLabel.CENTER);
        //adds padding
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // top, left, bottom, right
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);
        row++;

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;

        // Fields array: label, type, optional options
        Object[][] fields = {
            {"Property Address", "textfield"},
            {"Monthly Rent", "textfield"},
            {"Property Type", "combobox", new String[]{"Apartment","House","Flat","TownHouse"}},
            {"Bedrooms", "textfield"},
            {"Bathrooms", "textfield"},
            {"Status", "combobox", new String[]{"Available","Occupied","Maintenance"}},
            {"Account Number", "textfield"}
        };

        int col = 0;
        for (int i = 0; i < fields.length; i++) 
        {
            String labelText = (String) fields[i][0];
            String type = (String) fields[i][1];

            gbc.gridx = col;
            gbc.gridy = row;
            add(new JLabel(labelText + ":"), gbc);

            JComponent comp;

            switch (type.toLowerCase()) {
                case "textfield":
                    comp = new JTextField(10);
                    addFocusHighlight(comp);
                    if ("Account Number".equals(labelText)) 
                    {
                        ((JTextField) comp).setText(UIHelper.generateAccountNumber());              //-Calls a method to generate a new account number
                        ((JTextField) comp).setEditable(false);                                     //-Sets field as a read-only
        }
                    break;
                case "combobox":
                    comp = new JComboBox<>((String[]) fields[i][2]);
                    break;
                default:
                    comp = new JTextField(10);
                    addFocusHighlight(comp);
            }

            gbc.gridy = row + 1;
            add(comp, gbc);
            components.put(labelText, comp);

            col++;

            // Switch for row breaks
            switch (i) {
                case 1: // after Monthly Rent
                case 4: // after Bathrooms
                case 5: // after Status
                    row += 2;
                    col = 0;
                    break;
            }
        }

        // Buttons: Save and Cancel
        row += 2;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.CENTER;
        JButton saveBtn = new JButton("Add Property");
        JButton cancelBtn = new JButton("Cancel");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3;
        add(buttonPanel, gbc);
    }

    private void addFocusHighlight(JComponent comp) {
        comp.addFocusListener(new FocusAdapter() {
            Border original = comp.getBorder();
            @Override
            public void focusGained(FocusEvent e) {
                comp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            @Override
            public void focusLost(FocusEvent e) {
                comp.setBorder(original);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        StyleHelper.paintCard(g, getWidth(), getHeight(), cornerRadius, borderThickness, borderColor, bgColor);
    }

    
    public String getFieldValue(String label) 
    {
        JComponent comp = components.get(label);
        if (comp instanceof JTextField tf) return tf.getText();
        if (comp instanceof JComboBox<?> cb) return (String) cb.getSelectedItem();
        if (comp instanceof JScrollPane sp) 
        {
            JTextArea ta = (JTextArea) ((JScrollPane) comp).getViewport().getView();
            return ta.getText();
        }
        return null;
    }
}
