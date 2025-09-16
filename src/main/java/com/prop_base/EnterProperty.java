package com.prop_base;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnterProperty extends JPanel 
{
    public EnterProperty() 
    {
    
    //-Step 1: Create the UI card
        EnterPropertyCard card = new EnterPropertyCard();
        add(card);

    //-Step 2: Access the save button
        JButton saveBtn = card.getSaveButton(); 
        saveBtn.setBackground(Color.BLUE);
        saveBtn.setForeground(Color.WHITE);

    //-Step 3: Add the logic in the parent class
        saveBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent a) 
            {

            //-Access the fields from the card's component map
                JTextField rentField = (JTextField) card.getComponentMap().get("Monthly Rent");
                JTextField bedroomsField = (JTextField) card.getComponentMap().get("Bedrooms");
                JTextField bathroomsField = (JTextField) card.getComponentMap().get("Bathrooms");
                JTextField addressField = (JTextField) card.getComponentMap().get("Property Address");

            //-Validate required text fields
                if (addressField.getText().trim().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(EnterProperty.this,
                            "Property Address cannot be empty.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    addressField.requestFocus();
                    return;
                }

            //-Validates numeric fields
                if (!ValidationsHelper.amountValidation(rentField, "Monthly Rent", true)) return;
                if (!ValidationsHelper.amountValidation(bedroomsField, "Bedrooms", false)) return;
                if (!ValidationsHelper.amountValidation(bathroomsField, "Bathrooms", false)) return;

            //-Save to JSON only if validations pass
                ValidationsHelper.saveToJson(card);
                UIHelper.resetPropertyCard(card);                                                   //-Reset the form and provides a new account number after saving
            }
        });
    }
}
