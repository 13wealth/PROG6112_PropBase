package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * PropertyRentalReport is a JPanel that displays a report of rental properties
 * - Sets up a styled card layout and adds logic to display property data in a table
 * - Reads the JSON file (via ValidationsHelper)
 * - Populates a JTable with property details from the JSON file
 */
public class PropertyRentalReport extends JPanel 
{
    private static final String JSON_FILE = "AllProperties.json";
    private final int cornerRadius = 20;                                                            //-Card corner radius
    private final Color bgColor = Color.WHITE;                                                      //-Card background color
    private final JLabel cardTitle;                                                                 //-Card title
    private final JTable reportTable;                                                               //-Table to display JSON data

    public PropertyRentalReport() 
    {
        
    //-Setup and card layout
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setPreferredSize(new Dimension(700, 400));

        GridBagConstraints cardProperties = new GridBagConstraints();
        cardProperties.insets = new Insets(10, 10, 10, 10);                                         //-Padding around components
        cardProperties.fill = GridBagConstraints.HORIZONTAL;                                        //-Default stretch horizontally
        cardProperties.weightx = 1;                                                                 //-Allow horizontal expansion

        int row = 0;                                                                                //-Tracks current row

    //-Adds the card title
        cardTitle = StyleHelper.createLabel(
                                            "Property Report", 
                                            Font.CENTER_BASELINE, 
                                            SwingConstants.CENTER
        );
        StyleHelper.styleLabel(
                                cardTitle, 
                                20, 0, 20, 0, 
                                null, 0, 0, 
                                Color.BLUE, Color.LIGHT_GRAY, 
                                0, 0
        );
        cardProperties.gridx = 0;                                                                   //-Sets the component in the first column
        cardProperties.gridy = row;                                                                 //-Sets the current row
        cardProperties.gridwidth = 3;                                                               //-Spans 3 columns
        cardProperties.anchor = GridBagConstraints.CENTER;
        add(cardTitle, cardProperties);

        row++;
        cardProperties.gridwidth = 1;                                                               //-Resets gridwidth for next components

    //-Adds the report table
        reportTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(reportTable);

        cardProperties.gridx = 0;
        cardProperties.gridy = row;
        cardProperties.gridwidth = 2;                                                               //-Table spans 2 columns
        cardProperties.fill = GridBagConstraints.BOTH;                                              //-Fill horizontally & vertically
        cardProperties.weightx = 1;
        cardProperties.weighty = 1;
        add(scrollPane, cardProperties);

        row++;

    //-Populates the table with data from JSON file
        String[] columnNames = {
                                "Account", "Address", "Rent", "Type", 
                                "Bedrooms", "Bathrooms", "Status"
                               };
                               
        DefaultTableModel table = new DefaultTableModel(columnNames, 0); 

        try (FileInputStream streamObj = new FileInputStream(JSON_FILE))                            //-Opens the JSON file for reading and ensures it gets closed
        {
            JSONArray jsonArray = new JSONArray(new JSONTokener(streamObj));                        //-Parses the JSON file into a JSONArray

            for (int i = 0; i < jsonArray.length(); i++) 
            {
                JSONObject property = jsonArray.getJSONObject(i);                                   //-Gets each property object
                DecimalFormat currency = new DecimalFormat("#,##0.00");                                   //-Formats numbers to 2 decimal places
                table.addRow(new Object[]                                                           //-Adds a new row to the table with property details
                {
                    property.optString("Account Number"),
                    property.optString("Property Address"),
                    currency.format(property.optDouble("Monthly Rent")),                              //-Displays the monthly rent with 2 decimal places
                    property.optString("Property Type"),
                    property.optString("Bedrooms"),
                    property.optString("Bathrooms"),
                    property.optString("Status"),
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        reportTable.setModel(table);                                                                //-Sets the table model to the populated data
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);                                                                    //-Ensures the panel is properly rendered
        StyleHelper.paintCard(
                g,
                getWidth(),
                getHeight(),
                cornerRadius,
                0,
                Color.LIGHT_GRAY,
                bgColor
        );
    }
}
