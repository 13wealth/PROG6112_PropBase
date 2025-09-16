package com.prop_base;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel  extends JPanel                                                              //-Home panel inherits JPanel (Becomes a JPanel)
{                                                                                                   
    public HomePanel() 
    {
    
//STEP 1: CREATE LAYOUT AND ADD STYLING   

        setLayout(new BorderLayout());                                                              //-Sets a layout manager
        setBackground(new Color(60, 60, 60));                                                       //-Sets a background color for the main panel

//STEP 2: ADD PANELS TO THE LAYOUT
    //+++ TOP PANEL
        TopPanel topPanel = new TopPanel(                                                           //-Gets the name and surname that was captured and diplays it in coloum 3
                                        ValidationsHelper.getName(), 
                                        ValidationsHelper.getSurname()
        );       
        add(topPanel, BorderLayout.NORTH);
    
    //+++ SIDEBAR PANEL
        SideBarPanel sidebarPanel = new SideBarPanel();                                             //-Creates a new instance of SidebarPanel and assigns it to sidebarPanel
        add(sidebarPanel, BorderLayout.WEST);                                                       //-Adds the sidebar panel to the west side of the main panel
    
    //+++ MAIN CONTENT PANEL
        DisplayPanel displayPanel = new DisplayPanel();
        add(displayPanel, BorderLayout.CENTER); 

//STEP 3: ENTER LOGIC THAT HANDLES SCREEN CHANGES
    //+++ ENTER PROPERTY
        JButton addProp = sidebarPanel.getEnterPropertyButton();
        addProp.addActionListener(e -> 
        {
            displayPanel.addScreen(new EnterProperty());                                            //-Adds the EnterProperty screen to the display panel
        });

    //+++ SEARCH PROPERTY    
        JButton searchProp = sidebarPanel.getSearchPropertiesButton();
        searchProp.addActionListener(e -> 
        {
            displayPanel.addScreen(new SearchProperty());
        });

    //+++ UPDATE PROPERTY
        JButton updateProp = sidebarPanel.getUpdatePropertyButton();
        updateProp.addActionListener(e ->
        {
            displayPanel.addScreen(new UpdateProperty());
        });

        //+++ EXIT BUTTON
        //sidebarPanel.getExitButton().addActionListener(e -> System.exit(0));                        //-When the "Exit" button is clicked, exit the application

    }
}   