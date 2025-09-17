package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DeletePropertyCard extends JPanel 
{
    private final int cornerRadius = 20;
    private final Color bgColor = Color.WHITE;
    private final JTextField deleteField;
    private final JButton deleteButton;
    private final JTable resultsTable;
    private final JLabel cardTitle;

    public DeletePropertyCard() 
    {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setPreferredSize(new Dimension(700, 400));

        GridBagConstraints cardProperties = new GridBagConstraints();                               //-Instantiate GridBagConstraints with a cardProperties object
        cardProperties.insets = new Insets(10, 10, 10, 10);                                         //-Padding around the component
        cardProperties.fill = GridBagConstraints.HORIZONTAL;                                        //-Tells the layout manager how the component should resize inside its cell

        int row = 0;                                                                                //-Current row
        cardProperties.weightx = 1;                                                                 //-Allows component to grow horizontally when extra space is available                                      
    
    //-Sets up the card Title    
        cardTitle = StyleHelper.createLabel(
                                            "Delete Property", 
                                            Font.CENTER_BASELINE,
                                            SwingConstants.CENTER
                    );
                    StyleHelper.styleLabel(
                                            cardTitle, 
                                            20, 0, 20, 0, 
                                            null, 0, 
                                            0, Color.BLUE, Color.LIGHT_GRAY, 
                                            0, 0
        );
        cardProperties.gridx = 0;                                                                   //-Places the component in Column 0 = First column
        cardProperties.gridy = row;                                                                 //-Places the component in Row 0 = First row
        cardProperties.gridwidth = 3;                                                               //-titleLabel will stretch across columns 0, 1, and 2 in the first row
        cardProperties.anchor = GridBagConstraints.CENTER;                                          //-Sets the component's position within its cell
        add(cardTitle, cardProperties);
        
        row++;
        cardProperties.gridwidth = 1;

    //-Sets up the component's label and field
        deleteField = new JTextField(15);
        StyleHelper.addLabeledField(this, "Account Number:", deleteField, row++);
        cardProperties.gridx = 1;
        
        row++; 

    //-Sets up the Results Table
        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        cardProperties.gridx = 0;                                                                   //-Places the component in Column 0 = First column
        cardProperties.gridy = row;                                                                 //-Places the component in Row 0 = First row
        cardProperties.gridwidth = 2;                                                               //-Sets the component to span 2 columns
        cardProperties.fill = GridBagConstraints.BOTH;                                              //-Tells the layout manager how the component should resize inside its cell.
        cardProperties.weightx = 1;                                                                 //-Allows the component to grow horizontally
        cardProperties.weighty = 1;                                                                 //-Allows the component to grow vertically
        add(scrollPane, cardProperties);                                                            //-Adds the scroll pane to the card layout

        row++;

    //-Sets up the Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);                                                      

        cardProperties.gridx = 0;
        cardProperties.gridy = row;
        cardProperties.gridwidth = 2;
        cardProperties.fill = GridBagConstraints.NONE;                                              //-Don't stretch
        cardProperties.anchor = GridBagConstraints.CENTER;                                          //-Center in cell
        cardProperties.weightx = 0;                                                                 //-Remove horizontal expansion
        cardProperties.weighty = 0;                                                                 //-Remove vertical expansion
        add(deleteButton, cardProperties);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);                                                                    //-Paints the component
        StyleHelper.paintCard(                                                                      //-Sets predifined styles for the card
                                g,
                                getWidth(),
                                getHeight(),
                                cornerRadius,
                                0,
                                Color.LIGHT_GRAY,
                                bgColor
        );
    }

    /** Getters */
    public JTextField getDeleteField() { return deleteField; }
    public JButton getDeleteButton() { return deleteButton; }
    public JTable getResultsTable() { return resultsTable; }
    public String getDeleteQuery() { return deleteField.getText().trim(); }
}