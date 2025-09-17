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

public class SearchPropertyCard extends JPanel 
{

    private final int cornerRadius = 20;
    private final Color bgColor = Color.WHITE;

    private final JButton searchBtn;
    private final JTextField searchField;
    private final JTable resultsTable;
    private final JLabel cardTitle;

    public SearchPropertyCard() 
    {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setPreferredSize(new Dimension(700, 400));

        GridBagConstraints cardProperties = new GridBagConstraints();                               //-Instantiate GridBagConstraints with a cardProperties object
        cardProperties.insets = new Insets(10, 10, 10, 10);
        cardProperties.fill = GridBagConstraints.HORIZONTAL;
        cardProperties.weightx = 1;

        int row = 0;                                                                                //-Current row

                                  
        cardProperties.insets = new Insets(10, 10, 10, 10);                                         //-Padding around the component
        cardProperties.fill = GridBagConstraints.HORIZONTAL;                                        //-Tells the layout manager how the component should resize inside its cell.
        
        cardProperties.weightx = 1;                                                                 //-The card should expand horizontally to take available space when the window is resized
    
    //-Card Title    
        cardTitle = StyleHelper.createLabel(
                                            "Search Property", 
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

        row++;                                                                                      //-Move to next row
        cardProperties.gridwidth = 1;                                                                  //-Reset gridwidth

    //-Search label
        cardProperties.gridx = 0;
        cardProperties.gridy = row;
        add(new JLabel("Account Number:"), cardProperties);

    //-Search field
        searchField = new JTextField(15);
        cardProperties.gridx = 1;
        StyleHelper.highlightActive(searchField);                                                   //-Highlight on focus
        add(searchField, cardProperties);

        row++;                                                                                      //-Move to next row

    //-Search button
        searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(0, 102, 204));                                            //-Blue
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        cardProperties.gridx = 0;
        cardProperties.gridy = row;
        cardProperties.gridwidth = 2;
        cardProperties.anchor = GridBagConstraints.CENTER;
        add(searchBtn, cardProperties);

        row++;                                                                                      //-Move to next row

    //-Results table
        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        cardProperties.gridx = 0;
        cardProperties.gridy = row;
        cardProperties.gridwidth = 2;
        cardProperties.fill = GridBagConstraints.BOTH;
        cardProperties.weightx = 1;
        cardProperties.weighty = 1;                                                                            //-Allows table to expand vertically
        add(scrollPane, cardProperties);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        StyleHelper.paintCard(                                                                      //-Creates rounded card background
                                g,
                                getWidth(), getHeight(),
                                cornerRadius, 0,
                                Color.LIGHT_GRAY, bgColor
        );
    }

    /** 
     * Getters for the search property card components
     */
    public JButton getSearchButton() { return searchBtn; }
    public String getSearchQuery() { return searchField.getText().trim(); }
    public JTable getResultsTable() { return resultsTable; }
}
