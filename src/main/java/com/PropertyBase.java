package com;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.prop_base.HomePanel;
//import com.prop_base.ValidationsHelper;


public class PropertyBase 
{
    /**
     * Main method to launch the Property Base application
     * Creates a frame, adds the HomePanel, and sets frame properties
     * @param args
     */
    public static void main(String[] args) 
    {
       
        //ValidationsHelper.startMenu();
        SwingUtilities.invokeLater(() -> 
        {                                                      
            JFrame appWindow = new JFrame("Property Management System");                            //-Creates JFrame application window called "appWindow"
            appWindow.add(new HomePanel());                                                         //-Fills up the frame with HomePanel components
            appWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);                                      //-Sets the application window to be maximised
            appWindow.setSize(1000, 600);                                                           //-Sets the size of the application window when minimised
            appWindow.setLocationRelativeTo(null);                                                  //-Centers the frame on the screen
            appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                               //-Ensure the application exits when the frame is closed
            appWindow.setVisible(true);                                                             //-Make the frame visible
        });
    }
}
