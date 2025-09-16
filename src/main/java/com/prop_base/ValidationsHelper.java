package com.prop_base;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            JPanel menuPanel = new JPanel(new GridLayout(0, 1));                                    //-Creates a vertical panel for menu items
            menuPanel.add(new JLabel("<html>Enter '1' to Launch the Application.<br>" +
                                     "Enter any other key to exit.</html>"));
        //-Adds the menu items (Labels and Fields)
            menuPanel.add(new JLabel("Choice:"));
            menuPanel.add(launchField);

            menuPanel.add(new JLabel("First Name:"));
            menuPanel.add(nameField);

            menuPanel.add(new JLabel("Surname:"));
            menuPanel.add(surnameField);

            int result = JOptionPane.showConfirmDialog(
                                                        null,
                                                        menuPanel,
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
        File file = new File("AllProperties.json");                                                 //-File to save properties
        
        if (file.exists() && file.length() > 0)                                                     //-If file exists and is not empty, load it
        {
            String details = new String(Files.readAllBytes(file.toPath()));                         //-Reads the file content
            propertyArray = new JSONArray(details);                                                 //-Parses the content to a JSON array
        } else {
            propertyArray = new JSONArray();                                                        //-Creates a new JSON array if file doesn't exist or is empty
        }

    //-Creates a new property object
        JSONObject property = new JSONObject();
        property.put("Property Address", card.getFieldValue("Property Address"));
        property.put("Monthly Rent", card.getFieldValue("Monthly Rent"));
        property.put("Property Type", card.getFieldValue("Property Type"));
        property.put("Bedrooms", card.getFieldValue("Bedrooms"));
        property.put("Bathrooms", card.getFieldValue("Bathrooms"));
        property.put("Status", card.getFieldValue("Status"));
        property.put("Account Number", card.getFieldValue("Account Number"));

    //-Adds to array
        propertyArray.put(property);

    //-Saves back to file (overwrite with full array)
        try (FileWriter writer = new FileWriter(file)) 
        {
            writer.write(propertyArray.toString(4));                                   //-Prints the JSON array to the file with 4-space indentation
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
            File file = new File("AllProperties.json");
            if (!file.exists()) return null;

            String content = new String(Files.readAllBytes(Paths.get("AllProperties.json")));
            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) 
            {
                JSONObject obj = arr.getJSONObject(i);
                if (accountNumber.equals(obj.optString("Account Number"))) 
                {
                    return obj;
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
                "Property Address", "Monthly Rent", "Type",
                "Bedrooms", "Bathrooms", "Status", "Account Number"
            };

        DefaultTableModel model = new DefaultTableModel(columns, 0);                                //-Create a table model with the defined columns

        if (property != null) 
        {
            Object[] row = 
            {
                property.optString("Property Address"), 
                property.optString("Monthly Rent"),
                property.optString("Property Type"),
                property.optString("Bedrooms"),
                property.optString("Bathrooms"),
                property.optString("Status"),
                property.optString("Account Number")
            };
            model.addRow(row);                                                                      //-Adds the property details to the table model
        }
        table.setModel(model);                                                                      //-Sets the table model to the results
    }

    
    /**
     * Loads property data from the JSON file
     */
    public static void loadProperties() 
    {
        try 
        {
            File file = new File("AllProperties.json");
            if (!file.exists() || file.length() == 0)                                               //-File doesn't exist or is empty
            {
                propertyArray = new JSONArray(); 
                    return;                                                                         //-Stop loading if file is empty
            }
            
            String content = new String(Files.readAllBytes(file.toPath()));                         //-Reads the Json file 
            propertyArray = new JSONArray(content);                                                 //-Parses the Json file content into a JSONArray
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
            field.requestFocus();                                                                   //-Set focus back to the field
            return false;                                                                           //-Field is invalid
        }

        try 
        {
            if (allowDecimal) 
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
     * Getters to access private static variables
     * @return
     */
    public static String getName() { return name; }
    public static String getSurname() { return surname; }
    public static JSONArray getPropertyArray() { return propertyArray; }
}
