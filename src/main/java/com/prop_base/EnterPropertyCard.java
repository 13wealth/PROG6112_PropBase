package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EnterPropertyCard extends JPanel 
{
    private final int cornerRadius = 20;
    private final int borderThickness = 0;
    private final Color borderColor = new Color(200, 200, 200);
    private final Color bgColor = Color.WHITE;

    private final java.util.Map<String, JComponent> components = new java.util.HashMap<>();         //-This is a map called components
    private final JLabel cardTitle;
    private final JButton saveBtn;

    /**
     * This method creates a card for capturing new property details
     * getFieldValue() will be used to retrieve data from the form
     * Method -> Hooked to components logic -> Parsed to the display panel
     */
    public EnterPropertyCard() 
    {
        setLayout(new GridBagLayout());
        setOpaque(true);                                                                            //-The card paints its background (fills the rectangular area with its background color)
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));                                 //-Sets padding of 40 pixels on all sides
        setPreferredSize(new Dimension(900, 500));                                                  //-Sets the preferred size of the card

        GridBagConstraints cardProperties = new GridBagConstraints();                               //-Instantiate GridBagConstraints with a cardProperties object
        cardProperties.insets = new Insets(10, 10, 10, 10);
        cardProperties.fill = GridBagConstraints.HORIZONTAL;                                        //-Tells the layout manager how the component should resize inside its cell.
        
        cardProperties.weightx = 1;                                                                 //-The card should expand horizontally to take available space when the window is resized
        int titleRow = 0;

    //-Sets the Card title
        cardTitle = StyleHelper.createLabel(
                                            "Add New Property", 
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
        cardProperties.gridy = titleRow;                                                            //-Places the component in Row 0 = First row
        cardProperties.gridwidth = 6;                                                               //-titleLabel will stretch across columns 0, 1, and 2 in the first row
        cardProperties.anchor = GridBagConstraints.CENTER;                                          //-Sets the component's position within its cell
        add(cardTitle, cardProperties);

        titleRow++;                                                                                      //-Increase row counter by 1

        cardProperties.gridwidth = 1; cardProperties.anchor = GridBagConstraints.WEST;              //-The component will only take 1 cell (column) in the grid

    //-Fields array: label, type, optional options
    //-Code assisted by Open AI
        Object[][] fields =                                                                         //-Field definitions using dual arrays
        {
            {"Account Number", "textfield"},
            {"Property Address", "textfield"},
            {"Monthly Rent", "textfield"},
            {"Property Type", "combobox", new String[]{"Apartment","House","Flat","TownHouse"}},    //-Adds the new property types
            {"Bedrooms", "textfield"},
            {"Bathrooms", "textfield"},
            {"Status", "combobox", new String[]{"Available","Occupied","Maintenance"}},             //-Adds the new property statuses
            {"Agent", "textfield"}
        };
                                                                                                    
        int[][] rowMap = {                                                                          //-Maps the fields to their respective rows
            {0, 1, 2},
            {3, 4, 5},
            {6, 7}
                         };
        
        int fieldsRow = 1;                                                                          //-Start at row 1 (row 0 = title)          
                    
        for (int[] group : rowMap) 
        {
            int col = 0;
        //-Inner loop for building the fields
            for (int i : group) 
            {
                String labelText = (String) fields[i][0];                                           //-Creates the label text
                String type = (String) fields[i][1];                                                //-Creates the field type
            //-Adds the label to the grid
                cardProperties.gridx = col++;                                                           //-Sets the column position
                cardProperties.gridy = fieldsRow;                                                       //-Sets the row position
                add(new JLabel(labelText + ":"), cardProperties);                                       //-Adds the label to the grid

            //-Switch statement to build the fields
                JComponent comp;                                                                        //-Declares a component variable
                switch (type.toLowerCase())         
                {
                    case "textfield" ->                                                                 //-Switch statement for text fields
                    {                                                                   
                        comp = new JTextField(10);                                                      //-Creates a text field with 10 columns
                        StyleHelper.highlightActive(comp);                                              //-Adds focus highlighting to the component
                        
                        if ("Account Number".equals(labelText)) 
                        {
                            ((JTextField) comp).setText(UIHelper.generateAccountNumber());          //-Calls a method to generate a new account number
                            ((JTextField) comp).setEditable(false);                                 //-Sets field as a read-only
                        }
                    }

                    case "combobox" -> comp = new JComboBox<>((String[]) fields[i][2]);                 //-Switch statement for combo boxes
                    default -> 
                    {
                        comp = new JTextArea(3, 20);                                                //-Default to 3 rows, 20 columns
                        StyleHelper.highlightActive(comp);
                    }
                }
            //-Adds field to grid
                cardProperties.gridx = col++;
                cardProperties.gridy = fieldsRow;
                add(comp, cardProperties);
                components.put(labelText, comp);                                                    //-Maps the label to the component
            }
            fieldsRow++;
        }

    //-The Save button
        saveBtn = new JButton("Add Property");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(saveBtn);

        cardProperties.gridx = 0;
        cardProperties.gridy = fieldsRow + 1;                                                       //-Below last row
        cardProperties.gridwidth = 6;                                                               //-Span across whole form
        cardProperties.anchor = GridBagConstraints.CENTER;

        add(buttonPanel, cardProperties);                                                           //-Adds the save button to the panel                                                                                                 
    }
    
    
    /**
     * Paints the component with a custom background and border
     * Provides rounded corners
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);                                                                    //-Calls the original paintComponent method from the parent class (like JPanel)
        StyleHelper.paintCard(
                                g, 
                                getWidth(), getHeight(), 
                                cornerRadius, borderThickness, 
                                borderColor, bgColor);
    }


    /**
     * Gets the current value of any form field (fields, combo boxes) by using its label
     * Will be used later for form submission and saving to JSON
     * - Each field in your form has a label (used as a key in your components map)
     * - You call getFieldValue(label) for each field to get the current value as a String
     * - You then store those key-value pairs in your JSON object
     *
     * @param label The label of the form field
     * @return      The current value of the form field as a String
     */
    public String getFieldValue(String label) 
    {
        JComponent comp = components.get(label);
        if (comp instanceof JTextField text)                                                        //-If component is a JTextField, return the text entered in the field: text.getText()
            return text.getText();
        if (comp instanceof JComboBox<?> dropDown)                                                  //-If component is a JComboBox, return the selected item
            return (String) dropDown.getSelectedItem();
        return null;                                                                                //-If component is not recognized, return null
    }

    /**
     * Exposes the save button for adding a new property
     * @return The button used to save the property details
     */
    public JButton getSaveButton() { return saveBtn; }
    public java.util.Map<String, JComponent> getComponentMap() { return components; }
}