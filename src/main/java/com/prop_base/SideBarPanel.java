package com.prop_base;

import java.awt.BorderLayout;
import java.awt.Color;
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
        setLayout(new BorderLayout());                                                              //-Sets layout to BorderLayout
        setPreferredSize(new Dimension(200, 0));                                                    //-Sets preferred width to 200px, height to auto
        setBackground(StyleHelper.LOGO_BG);                                                         //-Sets background color to logo color

    //-Splits the SideBarPanel into top and bottom sections
        JPanel topSection = new JPanel(new GridLayout(0, 1, 10, 10));                               //-Top section for main buttons 0 rows, 1 column, 10px hgap, 10px vgap
        topSection.setOpaque(false);                                                                //-Makes background transparent
        topSection.setBorder(BorderFactory.createEmptyBorder(70, 36, 10, 36));                       //-Applies padding of 70px top, 36px left/right, 10px bottom

        JPanel bottomSection = new JPanel(new GridLayout(1, 1));
        bottomSection.setOpaque(false);                                                             //-Makes background transparent
        bottomSection.setBorder(BorderFactory.createEmptyBorder(10, 36, 20, 36));                   //-Applies padding of 10px top, 36px left/right, 20px bottom

        String[] buttonLabels = {                                                                   //-Adds an array of button labels
                                 "Add New Property", 
                                 "Search Properties", 
                                 "Update Property", 
                                 "Delete Property", 
                                 "Print All Properties", 
                                 "Logout", 
                                }; 

        for (int i = 0; i < buttonLabels.length; i++) 
        {
            JButton button = new JButton(buttonLabels[i]);                                          //-Creates a new button with the label
            sidebarButton.put(buttonLabels[i], button);                                             //-Adds the button to the map with the label as the key
            StyleHelper.styleButton(button);                                                 //-Styles the button using the StyleHelper

            if (i == buttonLabels.length - 1)                                                       //-Length - 1 is the logout button
            {
                button.setForeground(new Color(220, 53, 69));
                bottomSection.add(button);                                                          //-If condition is met, add button to bottom section
            
            } else {
                topSection.add(button);                                                             //-All other buttons go to top section
            }

        }
        add(topSection, BorderLayout.NORTH);                                                        //-Adds the top section to the north
        add(bottomSection, BorderLayout.SOUTH);                                                     //-Adds the bottom section to the south
    }

    /**
     * Getters for HomePanel to attach navigation logic
     * @return
     */
    public JButton getEnterPropertyButton() { return sidebarButton.get("Add New Property"); }
    public JButton getSearchPropertiesButton() { return sidebarButton.get("Search Properties"); }
    public JButton getUpdatePropertyButton() { return sidebarButton.get("Update Property"); }
    public JButton getDeletePropertyButton() { return sidebarButton.get("Delete Property"); }
    public JButton getPrintAllPropertiesButton() { return sidebarButton.get("Print All Properties"); }
    public JButton getLogoutButton() { return sidebarButton.get("Logout"); }

}

