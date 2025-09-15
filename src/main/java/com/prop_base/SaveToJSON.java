package com.prop_base;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class SaveToJSON 
{
    public static void saveToJson(EnterPropertyCard card) 
    {
        try {
            JSONObject property = new JSONObject();

        //-Adds all field values
            property.put("Property Address", card.getFieldValue("Property Address"));
            property.put("Monthly Rent", card.getFieldValue("Monthly Rent"));
            property.put("Property Type", card.getFieldValue("Property Type"));
            property.put("Bedrooms", card.getFieldValue("Bedrooms"));
            property.put("Bathrooms", card.getFieldValue("Bathrooms"));
            property.put("Status", card.getFieldValue("Status"));
            property.put("Account Number", card.getFieldValue("Account Number"));

        //-Append JSON to file
            try (FileWriter file = new FileWriter("AllProperties.json", true)) 
            {
                file.write(property.toString(4));                                      //-Writes property details to JSON with 4-space indentation
                file.write(System.lineSeparator());                                                 //-Adds a newline after each property entry
            }

            JOptionPane.showMessageDialog(
                                            null,
                                            "Property saved successfully!",
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE
                                         );
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
