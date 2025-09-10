package com.prop_base;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SideBarPanel extends JPanel
{
    private final Map<String, JButton> sidebarButton = new LinkedHashMap<>();  
    /*
        Notes from Open AI:
        private -> Only this class can access sidebarButtons
        final -> sidebarButtons cannot be reassigned, can't do sidebarButtons = new ArrayList<>();
        Map -> Interface that LinkedHashMap implements
        List<JButton> -> You can still add/remove elements inside the list
        LinkedHashMap -> Adds elements in the order they are defined
     */

    public SideBarPanel() 
    {
        setLayout(new GridLayout(6, 1, 10, 10));                                                    //-Sets layout of 6 rows, 1 column, 10px horizontal and vertical gaps
        setBorder(BorderFactory.createEmptyBorder(20, 18, 20, 18));                                 //-Sets border of 22px top/bottom and 15px left/right
        setPreferredSize(new Dimension(200, 0));
        setBackground(StyleHelper.LOGO_BG);


        String[] buttonLabels = {                                                                   //-Adds an array of button labels
                                 "Add New Property", 
                                 "Search Properties", 
                                 "Update Property", 
                                 "Delete Property", 
                                 "Print All Properties", 
                                 "Logout", 
                                }; 

        for (String label : buttonLabels) 
        {
            JButton button = new JButton(label);                                                    //-Creates a new button with the label
            sidebarButton.put(label, button); 
            StyleHelper.styleSidebarButton(button);                                                 //-Adds the button to the map with the label as the key
            add(button);                                                                            //-Adds the button to the panel
        }
    }
}
