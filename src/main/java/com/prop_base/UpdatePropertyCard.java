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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UpdatePropertyCard extends JPanel 
{
    private final int cornerRadius = 20;
    private final Color bgColor = Color.WHITE;
    private final JTextField accountField;
    private final JButton searchBtn;
    private final JButton updateBtn;
    private final JLabel cardTitle;
//-Editable property fields
    private final JTextField addressField;
    private final JTextField rentField;
    private final JTextField typeField;
    private final JTextField bedroomsField;
    private final JTextField bathroomsField;
    private final JTextField statusField;
    private final JTextField agentField;

    public UpdatePropertyCard() 
    {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(30, 30, 30, 30));                                                 //-Sets the border for the card
        setPreferredSize(new Dimension(700, 500));

        GridBagConstraints cardProperties = new GridBagConstraints();                               //-Instantiate GridBagConstraints with a cardProperties object
        cardProperties.insets = new Insets(8, 8, 8, 8);                                             //-Set the external padding for the component
        cardProperties.fill = GridBagConstraints.HORIZONTAL;                                        //-Tells the layout manager how the component should resize inside its cell.
        cardProperties.weightx = 1;                                                                 //-Allows the component to grow horizontally

        int row = 0;                                                                                //-Row counter for placing components
        cardProperties.weightx = 1;                                                                 //-The card should expand horizontally to take available space when the window is resized

        cardTitle = StyleHelper.createLabel(
                                            "Update Property", 
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
        cardProperties.gridwidth = 1;                                                               //-Reset gridwidth

        accountField = new JTextField(20);                                                          //-Create the account field
        StyleHelper.addLabeledField(this, "Account Number:", accountField, row++);
        StyleHelper.highlightActive(accountField);
        
        row++;

    //-Sets up Search Button
        searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(0, 102, 204));                                            //-Blue colour
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);                                                           //-Disable focus painting (Greys out the button)

    //-Sets up Update Button
        updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(102, 102, 102));                                          //-Grey colour
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);                                                           //-Disable focus painting (Greys out the button)
        updateBtn.setEnabled(false);                                                                //-Disable button

    //-Sets up Button Panel
        cardProperties.gridx = 0;                                                                   //-Puts button in the first column
        cardProperties.gridy = row;                                                                 //-Puts button in the current row
        cardProperties.gridwidth = 2;                                                               //-Makes the button span 2 columns
        JPanel btnPanel = new JPanel();                                                             //-Creates a panel to hold the buttons
        btnPanel.add(searchBtn);                                                                    //-Adds the search button to the panel
        btnPanel.add(updateBtn);                                                                    //-Adds the update button to the panel
        add(btnPanel, cardProperties);                                                              //-Adds the button panel to the card

        row++;
        cardProperties.gridwidth = 1; 

    //-Adds the form fields
        String[] labels = { 
                            "Property Address", "Monthly Rent", "Property Type",
                            "Bedrooms", "Bathrooms", "Status", "Agent"
                          };
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++)                                                     //-Loop through the labels array to create fields
        {
            fields[i] = StyleHelper.addLabeledField(this, labels[i] + ":", fields[i], row++);                  //-Calls a method that creates a label and field
            StyleHelper.highlightActive(fields[i]);                                                 //-Calls a method that highlights the active field
        }
    //-Assigns individual variables
            addressField   = fields[0];
            rentField      = fields[1];
            typeField      = fields[2];
            bedroomsField  = fields[3];
            bathroomsField = fields[4];
            statusField    = fields[5];
            agentField     = fields[6];
    }

    @Override
    protected void paintComponent(Graphics g)                                                       //-Creates a custom painted background with rounded corners
    {
        super.paintComponent(g);
        StyleHelper.paintCard(
                                g, 
                                getWidth(), getHeight(), 
                                cornerRadius, 0, 
                                Color.LIGHT_GRAY, bgColor
        );
    }

    /**  
     * Getters for accessing private components
     */
    public JTextField getAccountField() { return accountField; }
    public JButton getSearchButton() { return searchBtn; }
    public JButton getUpdateButton() { return updateBtn; }
    public JTextField getAddressField() { return addressField; }
    public JTextField getRentField() { return rentField; }
    public JTextField getTypeField() { return typeField; }
    public JTextField getBedroomsField() { return bedroomsField; }
    public JTextField getBathroomsField() { return bathroomsField; }
    public JTextField getStatusField() { return statusField; }
    public JTextField getAgentField() { return agentField; }
}
