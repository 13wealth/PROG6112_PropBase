package com.prop_base;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class HomePanel  extends JPanel                                                              //-Home panel inherits JPanel (Becomes a JPanel)
{                                                                                                   
    private final TopPanel topPanel;                                                                //-Declaration for the TopPanel           
    private final SideBarPanel sidebarPanel;                                                        //-Declaration for the SideBarPanel

    public HomePanel() 
    {
    
//STEP 1: CREATE LAYOUT, CREATE AND ADD PANELS   

            setLayout(new BorderLayout());                                                          //-Sets a layout manager
            setBackground(new Color(60, 60, 60));                                                   //-Sets a background color for the main panel

//STEP 2: CREATE LAYOUT, CREATE AND ADD PANELS               
        //+++ TOP PANEL
            topPanel = new TopPanel(ValidationsHelper.getName(), ValidationsHelper.getSurname());   //-Gets the name and surname that was captured and diplays it in coloum 3
            add(topPanel, BorderLayout.NORTH);
    
        //+++ SIDEBAR PANEL
            sidebarPanel = new SideBarPanel();                                                      //-Creates a new instance of SidebarPanel and assigns it to sidebarPanel
            add(sidebarPanel, BorderLayout.WEST);                                                   //-Adds the sidebar panel to the west side of the main panel
    }


        /**
         * Exposes SidebarPanel for navigation logic
         * Exposes RightPanel for metadata display
         * @return
         */
        public TopPanel getTopPanel() { return topPanel; }
    }   