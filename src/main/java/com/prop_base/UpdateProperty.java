package com.prop_base;

import java.awt.BorderLayout;
import java.io.FileWriter;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

/**
 * UpdateProperty is a JPanel that contains UpdatePropertyCard and handles all logic:
 * - Reads the JSON file
 * - Searches property by Account Number
 * - Loads JSON into the results table (allows for updates)
 * - Saves updated property details to JSON
 */
public class UpdateProperty extends JPanel 
{
    private final UpdatePropertyCard card;
    private int currentIndex = -1;

    public UpdateProperty() 
    {
        setLayout(new BorderLayout());
        card = new UpdatePropertyCard();
        add(card, BorderLayout.CENTER);                                                             //-Center the card in the panel

        ValidationsHelper.loadProperties();                                                         //-Calls a method to load content from the JSON file

        card.getSearchButton().addActionListener(e -> searchProperty());                            //-Search for a property
        card.getUpdateButton().addActionListener(e -> updateProperty());                            //-Update the property
    }

    
    /**
     * Searches the JSON file for a property by its Account Number
     * If there's a match, populates the fields with the property details
     * Logic assisted by Open AI
     */
    public void searchProperty() 
    {
        String accountNumber = card.getAccountField().getText().trim();
        if (accountNumber.isEmpty())                                                                //-Check if the Account Number field is empty
        {
            JOptionPane.showMessageDialog(this, "Please enter an Account Number.", "Input Error",   //-Prompt error message
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JSONObject property = null;                                                                 //-Sets the current property being searched to null
        currentIndex = -1;                                                                          //-Reset current index so after every search iteration it starts from 0

        for (int i = 0; i < ValidationsHelper.getPropertyArray().length(); i++)                     //-Iterates through the property array
        {
            JSONObject searchObj = ValidationsHelper.getPropertyArray().getJSONObject(i);           //-Gets the property object at index i
            if (accountNumber.equals(searchObj.optString("Account Number")))                    //-Checks if the Account Number matches
            {
                property = searchObj;                                                               //-Sets the property to the found object
                currentIndex = i;                                                                   //-Sets the current index to the found object's index
                break;                                                                              //-Breaks the loop once the property is found
            }
        }

        if (property == null)                                                                       //-If no property is found
        {
            JOptionPane.showMessageDialog(
                                          this, 
                                          "Property not found.", 
                                          "Error", 
                                          JOptionPane.ERROR_MESSAGE
                                         );
            card.getUpdateButton().setEnabled(false);                                               //-Disables the update button if no property is found
                clearFields();                                                                      //-Calls a method to clear all input fields
                    return;                                                                         //-Exits the method if no property is found
        }

    //-Else populate fields if property is found
        DecimalFormat df = new DecimalFormat("###0.00");                                           //-Sets the currency format
        
        card.getAddressField().setText(property.optString("Property Address"));
        card.getRentField().setText(df.format(property.optDouble("Monthly Rent")));             //-Formats the rent field
        card.getTypeField().setText(property.optString("Property Type"));
        card.getBedroomsField().setText(property.optString("Bedrooms"));
        card.getBathroomsField().setText(property.optString("Bathrooms"));
        card.getStatusField().setText(property.optString("Status"));
        card.getAgentField().setText(property.optString("Agent"));
        card.getUpdateButton().setEnabled(true);
    }

    
    /**
     * Updates the property details in the JSON array
     * 
     */
    public void updateProperty() 
    {
        if (currentIndex < 0) return;                                                               //-No property selected for update
        try 
        {
            if (!ValidationsHelper.PropertyAmountValidation(card.getRentField(), 
                                                            "Monthly Rent", 
                                                            true
                )) return;
            if (!ValidationsHelper.NumberValidation(card.getBedroomsField(), 
                                                    "Bedrooms", 
                                                    false
                )) return;
            if (!ValidationsHelper.NumberValidation(card.getBathroomsField(), 
                                                    "Bathrooms", 
                                                    false
                )) return;
            JSONObject updateObj = ValidationsHelper.getPropertyArray().getJSONObject(currentIndex);//-Gets the property object at currentIndex

        //-Update values from editable fields
            updateObj.put("Property Address", card.getAddressField().getText());
            updateObj.put("Monthly Rent", card.getRentField().getText());
            updateObj.put("Property Type", card.getTypeField().getText());
            updateObj.put("Bedrooms", card.getBedroomsField().getText());
            updateObj.put("Bathrooms", card.getBathroomsField().getText());
            updateObj.put("Status", card.getStatusField().getText());
            updateObj.put("Agent", card.getAgentField().getText());

        //-Save back to JSON file
            try (FileWriter writer = new FileWriter("AllProperties.json")) 
            {
                writer.write(ValidationsHelper.getPropertyArray().toString(4));        //-Save updated properties
            }

            JOptionPane.showMessageDialog(this, "Property updated successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        //-Reset UI
            card.getAccountField().setText("");
            card.getUpdateButton().setEnabled(false);
            clearFields();                                                                          //-Clear all fields
            currentIndex = -1;                                                                      //-Reset current index

        } catch (Exception ex) {
            ex.printStackTrace();                                                                   //-Logs the exception
            JOptionPane.showMessageDialog(
                                           this, 
                                           "Error updating property: " + ex.getMessage(),
                                           "Error",
                                            JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Clears all input fields in the update property form
     */
    private void clearFields() 
    {
        card.getAddressField().setText("");
        card.getRentField().setText("");
        card.getTypeField().setText("");
        card.getBedroomsField().setText("");
        card.getBathroomsField().setText("");
        card.getStatusField().setText("");
        card.getAgentField().setText("");
    }

    
    /**
     * Updates a property directly in the JSON array.
     * Backend helper function for JUnit testing purposes
     * @param index
     * @param data
     * @return
     */
    public boolean updatePropertyDirect(int index, String[] data) 
    {
        try 
        {
            JSONObject updateObj = ValidationsHelper.getPropertyArray().getJSONObject(index);

            updateObj.put("Account Number", data[0]);
            updateObj.put("Property Address", data[1]);
            updateObj.put("Monthly Rent", data[2]);
            updateObj.put("Agent", data[3]);

            try (FileWriter writer = new FileWriter("AllProperties.json")) {
                writer.write(ValidationsHelper.getPropertyArray().toString(4));
            }

            return true;  // success
        } catch (Exception e) {
            e.printStackTrace();
            return false; // failed
    }   
    }
}




/**
 * References
 * OpenAI. (2025, September 16). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */