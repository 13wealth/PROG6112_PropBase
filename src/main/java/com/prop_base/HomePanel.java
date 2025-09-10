package com.prop_base;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class HomePanel  extends JPanel                                                           //-Main panel inherits JPanel
{                                                         //-Declares the sidebar panel
    private TopPanel topPanel;                                                                  //-Declares the top panel
    
    /**
     * 
     */
    public HomePanel() 
    {
    
//STEP 1: CREATE LAYOUT, CREATE AND ADD PANELS   

            setLayout(new BorderLayout());                                                          //-Sets a layout manager
            setBackground(new Color(60, 60, 60));                                                   //-Sets a background color for the main panel

//STEP 2: CREATE LAYOUT, CREATE AND ADD PANELS               
        //+++ TOP PANEL
            topPanel = new TopPanel();                                                              //-Creates a new instance of TopPanel and assigns it to topPanel

            add(topPanel, BorderLayout.NORTH);
    }


        /**
         * Exposes SidebarPanel for navigation logic
         * Exposes RightPanel for metadata display
         * @return
         */
        public TopPanel getTopPanel() { return topPanel; }
    }   