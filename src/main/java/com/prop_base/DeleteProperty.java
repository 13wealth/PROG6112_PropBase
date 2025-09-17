package com.prop_base;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;


/**
 * DeleteProperty is a JPanel that contains DeletePropertyCard and handles UI logic:
 * - Reads the JSON file (via ValidationsHelper)
 * - Searches property by account number (Enter key)
 * - Deletes property by account number (Delete button)
 */
public class DeleteProperty extends JPanel 
{
    private final DeletePropertyCard card;

    public DeleteProperty() 
    {
        setLayout(new BorderLayout());
        card = new DeletePropertyCard();
        add(card, BorderLayout.CENTER);
        card.getDeleteButton().setEnabled(false);                                                   //-Disables the delete button until a valid account number is entered

    //-Sets up Action Listeners for 'Enter' key to search for property
        card.getDeleteField().addActionListener(e -> 
        {
            String accountNumber = card.getDeleteQuery();                                           //-Gets the account number from the text field
            if (accountNumber.isEmpty()) 
            {
                JOptionPane.showMessageDialog(null,
                                              "Please enter an Account Number.",
                                              "Input Error",
                                              JOptionPane.WARNING_MESSAGE);
                card.getDeleteButton().setEnabled(false);                                           //-Keeps the button disabled
                    return;
            }

            JSONObject property = ValidationsHelper.searchPropertyByAccount(accountNumber);         //-Instantiates a method that searches for the property by account number

            if (property == null)                                                                   //-If the search returns null
            {
                ValidationsHelper.clearResultsTable(card.getResultsTable());                        //-Clear previous results
                card.getDeleteButton().setEnabled(false);                                           //-Still keeps the button disabled
                JOptionPane.showMessageDialog(null,
                                            "No record found for account number: " + accountNumber,
                                            "Search Result",
                                            JOptionPane.INFORMATION_MESSAGE);
            } else {                                                                                //-Else if a property is found
                ValidationsHelper.updateResultsTable(card.getResultsTable(), property);             //-Update the table with the found property
                card.getDeleteButton().setEnabled(true);                                            //-And enable the delete button
            }
        });

    //-Sets up Action Listener for Delete button to delete property
        card.getDeleteButton().addActionListener(e -> 
        {
            String query = card.getDeleteQuery();                                                   //-Gets the account number from the text field
            if (query.isEmpty())                                                                    //-Validates that the field is not empty
            {
                JOptionPane.showMessageDialog(null,
                        "Please enter an Account Number to delete.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;                                                                             //-Exits the method if the field is empty
            }
        //-Else confirm deletion
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete property with Account Number: " + query + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION)                                                  //-If user confirms deletion (YES option)
            {
                boolean deleted = ValidationsHelper.deletePropertyByAccount(query);                 //-Calls the method to delete the property by account number (Updates JSON file after delete)
                if (deleted) 
                {
                    JOptionPane.showMessageDialog(null,
                            "Property deleted successfully!",
                            "Delete Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    card.getDeleteField().setText("");                                              //-Clears the input field
                    ValidationsHelper.clearResultsTable(card.getResultsTable());                    //-Clears the results table
                    card.getDeleteButton().setEnabled(false);                                       //-Disables the delete button again

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Property not found or could not be deleted.",
                            "Delete Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    });
}



    /**
     * Getters to access UI components
     * @return
     */
    public JPanel getCard() { return card; }
}
