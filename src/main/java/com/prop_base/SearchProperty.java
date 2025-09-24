package com.prop_base;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

/**
 * SearchProperty is a JPanel that contains SearchPropertyCard and handles all logic:
 * - Reads the JSON file
 * - Searches property by account number
 * - Loads JSON into the results table
 */
public class SearchProperty extends JPanel 
{
    public SearchProperty() 
    {
        setLayout(new BorderLayout());
        SearchPropertyCard card = new SearchPropertyCard();
        add(card, BorderLayout.CENTER);

    //-Hooks the search button to the search functionality
        card.getSearchButton().addActionListener(e -> 
        {
            String query = card.getSearchQuery();                                                   //-Calls a getter from the SearchPropertyCard
            if (query.isEmpty())                                                                    //-If the search query is empty
            {
                JOptionPane.showMessageDialog(null,
                        "Please enter an Account Number.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            JSONObject property = ValidationsHelper.searchPropertyByAccount(query);                 //-Creates a JSON object for the property (Calls the method that searches the property)
            
            if (property == null)
            {
                JOptionPane.showMessageDialog(null,
                        "No property found with Account Number: " + query,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                ValidationsHelper.updateResultsTable(card.getResultsTable(), property);                 //-Updates and displays the results (Calls the method that updates the results table)
            }
        });
    }    
}

