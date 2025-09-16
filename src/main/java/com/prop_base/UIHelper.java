package com.prop_base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class UIHelper 
{
    JLabel dateTime;

    /**
     * Updates the date/time label every second
     */
    public static void startClock(JLabel dateTime) 
    {
        Timer time = new Timer(1000, e -> 
        {
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    //-Gets the current date and time
            dateTime.setText(currentTime);                                                          //-Updates the date and time
        });
            time.start();                                                                           //-Starts the timer to update the date and time
    }
    

    /**
     * Resets all input fields in a property card
     * @param card The EnterPropertyCard to reset
     */
    public static void resetPropertyCard(EnterPropertyCard card) 
    {
        for (String key : card.getComponentMap().keySet())                                          //-Loop through all components in the card
        {
            JComponent comp = card.getComponentMap().get(key);                                      //-Gets the component for the given key

            if (comp instanceof JTextField textField)                                               //-If the component is a text field
            {
                if ("Account Number".equals(key))                                                   //-Check if the key matches "Account Number"
                {
                    textField.setText(generateAccountNumber());                                     //-Generate a new account number
                } else {
                    textField.setText("");                                                          //-Clear text fields
                }
            } else if (comp instanceof JTextArea textArea) {
                textArea.setText("");                                                               //-Clear text areas
            } else if (comp instanceof JComboBox<?> comboBox) {
                comboBox.setSelectedIndex(0);                                                       //-Reset combo boxes to first option
            }
        }
    }


    /**
     * Generates a unique account number.
     * @return A string representing the account number.
     */
    public  static String generateAccountNumber() 
    {
    
        return "ACC-" + (int)(Math.random() * 1000000);                                             //-Prefix + random 6 digits
    }
}
