package com.prop_base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
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
     * Generates a unique account number.
     * @return A string representing the account number.
     */
    public  static String generateAccountNumber() 
    {
    
        return "ACC-" + (int)(Math.random() * 1000000);                                             //-Prefix + random 6 digits
    }
}
