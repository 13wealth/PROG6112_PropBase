package com.prop_base;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class ValidationsHelper 
{

    private static final JTextField launchField = new JTextField();
    private static final JTextField nameField = new JTextField();
    private static final JTextField surnameField = new JTextField();

    private static String name;
    private static String surname;

    private static JSONArray propertyArray;
    private static final String JSON_FILE = "AllProperties.json";
    

    /**
     * Shows a startup panel asking for:
     * - Choice to launch (1 = launch, else exit)
     * - Name
     * - Surname
     * Returns a String array: {name, surname} if launched
     * Exits if cancelled or choice != 1
     */
    public static String[] startMenu()                                                              //-NOTE: Cannot create a new object for JTextField because variables are final
    {
        launchField.setText("");                                                                    //-Ensures that the fields clears only once, before the loop starts
        nameField.setText("");
        surnameField.setText("");

        while (true) 
        {
            JPanel menuObj = new JPanel(new GridLayout(0, 1));                                      //-Creates a vertical panel for menu items
            menuObj.add(new JLabel("<html>Enter '1' to Launch the Application.<br>" +
                                     "Enter any other key to exit.</html>"));
        //-Adds the menu items (Labels and Fields)
            menuObj.add(new JLabel("Choice:"));
            menuObj.add(launchField);

            menuObj.add(new JLabel("First Name:"));
            menuObj.add(nameField);

            menuObj.add(new JLabel("Surname:"));
            menuObj.add(surnameField);

            int result = JOptionPane.showConfirmDialog(
                                                        null,
                                                        menuObj,
                                                        "PROPBASE STARTUP",
                                                        JOptionPane.OK_CANCEL_OPTION,
                                                        JOptionPane.PLAIN_MESSAGE
                                                      );
            if (result == JOptionPane.OK_OPTION)                                                    //-If user clicked OK
            {
                String menuChoice = launchField.getText().trim();                                   //-Trim and get user input for menu choice
                name = nameField.getText().trim();                                                  //-Trim and get user input for name
                surname = surnameField.getText().trim();                                            //-Trim and get user input for surname

                if (menuChoice.isEmpty() || name.isEmpty() || surname.isEmpty())                    //-Checks if any field is empty
                {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                    continue;                                                                       //-Restarts the loop if validation fails
                }

                    if (!"1".equals(menuChoice))                                                    //-If input is not "1"
                    {
                        JOptionPane.showMessageDialog(null, "Goodbye 👋");
                        System.exit(0);
                    }

                        return new String[]{name, surname};                                         //-Success → return name + surname

                } else {
                    JOptionPane.showMessageDialog(null, "Goodbye 👋");                              //-If user cancels or close application
                    System.exit(0);                                                                 //-Ensures application terminates on close
            }
        }
    }


    /**
     * Saves the property details array from the card to a JSON file
     * @param card
     */
    public static void saveToJson(EnterPropertyCard card) 
    {
    try {
        File saveObj = new File(JSON_FILE);                                                         //-File to save properties

        if (saveObj.exists() && saveObj.length() > 0)                                               //-If file exists and is not empty, load it
        {
            String detailsObj = new String(Files.readAllBytes(saveObj.toPath()));                   //-Reads the file content
            propertyArray = new JSONArray(detailsObj);                                              //-Parses the content to a JSON array
        } else {
            propertyArray = new JSONArray();                                                        //-Creates a new JSON array if file doesn't exist or is empty
        }

    //-Creates a new property object
        JSONObject propertyObj = new JSONObject();
        propertyObj.put("Property Address", card.getFieldValue("Property Address"));
        propertyObj.put("Monthly Rent", card.getFieldValue("Monthly Rent"));
        propertyObj.put("Property Type", card.getFieldValue("Property Type"));
        propertyObj.put("Bedrooms", card.getFieldValue("Bedrooms"));
        propertyObj.put("Bathrooms", card.getFieldValue("Bathrooms"));
        propertyObj.put("Status", card.getFieldValue("Status"));
        propertyObj.put("Account Number", card.getFieldValue("Account Number"));

    //-Adds to array
        propertyArray.put(propertyObj);

    //-Saves back to file (overwrite with full array)
        try (FileWriter writerObj = new FileWriter(saveObj)) 
        {
            writerObj.write(propertyArray.toString(4));                                //-Prints the JSON array to the file with 4-space indentation
        }
            JOptionPane.showMessageDialog(null,
                "Property saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
        }
    }

    
    /**
     * Searches for a property by its account number.
     * @param accountNumber
     * @return
     */
    public static JSONObject searchPropertyByAccount(String accountNumber) 
    {
        try {
            File searchObj = new File(JSON_FILE);                                                   //-File to read properties
            if (!searchObj.exists()) return null;                                                   //-If file does not exist return null

            String contentObj = new String(Files.readAllBytes(searchObj.toPath()));                 //-Reads the json file content
            JSONArray arrayObj = new JSONArray(contentObj);                                         //-Parses the content to a JSON array object

            for (int i = 0; i < arrayObj.length(); i++) 
            {
                JSONObject IterObj = arrayObj.getJSONObject(i);
                if (accountNumber.equals(IterObj.optString("Account Number"))) 
                {
                    return IterObj;
                }
            }
                } catch (Exception ex) {
                    ex.printStackTrace();
            }
                return null;
        }


        /**
         * Updates the results table with the given property details
         * @param property
         * Method was assisted by Open AI
         */
        public static void updateResultsTable(JTable table, JSONObject property) 
        {
            String[] columns =                                                                      //-Define the column names
            {
                "Account", "Address", "Monthly Rent", "Type",
                "Bedrooms", "Bathrooms", "Status"
            };

        DefaultTableModel tableObj = new DefaultTableModel(columns, 0);                             //-Create a table model with the defined columns

        if (property != null)                                                                       //-If search is successful
        {
            DecimalFormat currency = new DecimalFormat("#,##0.00");                                 //-Formats numbers to 2 decimal places
            Object[] row =                                                                          //-Populate these keys from the property JSONObject
            {                
                property.optString("Account Number"),                                           
                property.optString("Property Address"), 
                currency.format(property.optDouble("Monthly Rent")),                            //-Displays the monthly rent formatted to 2 decimal places
                property.optString("Property Type"),
                property.optString("Bedrooms"),
                property.optString("Bathrooms"),
                property.optString("Status"),
            };
            tableObj.addRow(row);                                                                   //-Adds the property details to the table model
        }
        table.setModel(tableObj);                                                                   //-Sets the table model to the results
    }

    
    /**
     * Populates property data from the JSON file
     */
    public static void loadProperties() 
    {
        try 
        {
            File loadObj = new File(JSON_FILE);
            if (!loadObj.exists() || loadObj.length() == 0)                                         //-File doesn't exist or is empty
            {
                propertyArray = new JSONArray(); 
                    return;                                                                         //-Stop loading if file is empty
            }

            String contentObj = new String(Files.readAllBytes(loadObj.toPath()));                   //-Reads the Json file 
            propertyArray = new JSONArray(contentObj);                                              //-Parses the Json file content into a JSONArray
        } catch (Exception ex) {
            ex.printStackTrace();                                                                   //-Prints the stack trace for debugging
            propertyArray = new JSONArray();                                                        //-Initializes an empty JSONArray if an error occurs
        }
    }
    
      /**
     * Validates a number input field
     * 
     * @param field         The text field to validate
     * @param fieldName     The name of the field (for error messages)
     * @param allowDecimal  Whether to allow decimal numbers
     * @return              True if the field is valid, false otherwise.
     */
    public static boolean PropertyAmountValidation(
                                                    JTextField field, 
                                                    String fieldName, 
                                                    boolean allowDecimal
                                                  ) 
    {
        while (true)                                                                                //-Keep looping until valid input is entered 
        { 
            String text = field.getText().trim();                                                   //-Gets the trimmed text from the text field

            if (text.isEmpty())                                                                     //-Checks if the field is empty
            {
                JOptionPane.showMessageDialog(field, fieldName + " cannot be empty.",
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                field.requestFocus();
                    return false;                                                                   //-Stop if empty (or you can continue loop if you want to reprompt)
            }

            try {
                
                double rentValue;
                
                if (allowDecimal) 
                {
                    rentValue = Double.parseDouble(text);                                           //-Accept decimal values
                } else {
                    rentValue = Integer.parseInt(text);                                             //-Accept whole numbers
                }

                if (rentValue < 1500)                                                              //-Checks if the minimum value is 1500
                { 
                    JOptionPane.showMessageDialog(field, fieldName + " must be greater than 1500.",
                                                "Validation Error", JOptionPane.ERROR_MESSAGE);
                    field.requestFocus();                                                           //-Set focus back to the field
                        return false;                                                               //-Stop or continue loop if reprompting
                }
                    return true;                                                                    //-If valid number and greater than 1500

            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(field, fieldName + " must be a valid " +
                                            (allowDecimal ? "number." : "whole number."),
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                field.requestFocus();
                    return false;                                                                   //-Stop or continue loop if reprompting
            }
        }
    }

    
    /**
     * Validates a number input field
     * 
     * @param field         The text field to validate
     * @param fieldName     The name of the field (for error messages)
     * @param allowDecimal  Whether to allow decimal numbers
     * @return              True if the field is valid, false otherwise.
     */
    public static boolean amountValidation(
                                            JTextField field, 
                                            String fieldName, 
                                            boolean allowDecimal
                                          ) 
    {
        String text = field.getText().trim();

        if (text.isEmpty()) 
        {
            JOptionPane.showMessageDialog(field, fieldName + " cannot be empty.",
                                           "Validation Error", JOptionPane.ERROR_MESSAGE);
                field.requestFocus();                                                               //-Set focus back to the field
                    return false;                                                                   //-Field is invalid
        }

        try 
        {
            if (allowDecimal)                                                                       //-Allow decimal numbers
            {
                Double.parseDouble(text);                                                           //-Converts a string to a decimal number
            } else {
                Integer.parseInt(text);                                                             //-Converts a string to a whole number
            }
            return true;                                                                            //-Valid number
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(field, fieldName + " must be a valid " + 
                                           (allowDecimal ? "number." : "whole number."),
                                           "Validation Error", JOptionPane.ERROR_MESSAGE);
            field.requestFocus();                                                                   //-Set focus back to the field
            return false;                                                                           //-Field is invalid
        }
    }


    /**
     * Deletes a property by account number
     * @param accountNumber The account number of the property to delete
     * @return True if the property was deleted, false otherwise
     */
    public static boolean deletePropertyByAccount(String accountNumber) 
    {
        File deleteObj = new File(JSON_FILE);
        if (!deleteObj.exists()) return false;                                                      //-File does not exist return false and log the error

        try (FileReader readerObj = new FileReader(deleteObj))                                      //-Reads the JSON file
        {
            JSONArray arrayObj = new JSONArray(new org.json.JSONTokener(readerObj));                //-Parses the JSON file into a JSONArray after reading
            boolean found = false;                                                                  //-Flag to indicate if the property was found
            JSONArray updateObj = new JSONArray();                                                  //-Object to hold updated properties array

            for (int i = 0; i < arrayObj.length(); i++) 
            {
                JSONObject IterObj = arrayObj.getJSONObject(i); 
                if (accountNumber.equals(IterObj.optString("Account Number")))                  //-If input account number matches the Json key
                {
                    found = true;                                                                   //-Mark as found and skip this object to delete
                } else {
                    updateObj.put(IterObj);                                                         //-Add to updated properties array
                }
            }

            if (found) 
            {
                try (FileWriter writerObj = new FileWriter(deleteObj))                              //-Open FileWriter to write updated JSON
                {
                    writerObj.write(updateObj.toString(2));                             //-Overwrite JSON with indentation of 2 spaces
                }
            }

            return found;                                                                           //-Return true if property was found and deleted

        } catch (IOException e) {
            e.printStackTrace();                                                                    //-Log the error
            return false;
        }
    }
    
        
    /**
     * Clears the results table
     * @param table The JTable to clear
     */
    public static void clearResultsTable(JTable table) 
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();                             //-Gets the table model
        model.setRowCount(0);                                                                       //-Clears all rows from the table
    }
    
    
    /**
     * Getters to access private static variables
     * @return
     */
    public static String getName() { return name; }
    public static String getSurname() { return surname; }
    public static JSONArray getPropertyArray() { return propertyArray; }
}
