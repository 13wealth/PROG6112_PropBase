package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopPanel extends JPanel
{
   // private final JLabel appLogo;
    private final JLabel welcomeNote;

    public TopPanel() 
    {
//-Step 1: Set layout and background for top panel
        setLayout(new GridLayout(1, 3, 10, 10));                                                    //-Panel structure: 1 row, 3 columns, 10 pixels horizontal and vertical spacing
        setBackground(StyleHelper.DARK_BG);                                                         //-Parse a  StyleHelper method for dark header bar                                                         
        setPreferredSize(new Dimension(1000, 100));                                                 //-Sets the preferred size for the top panel

//-Step 2: Add components to the top panel
    //-1. Application Logo
        java.net.URL logoURL = getClass().getResource("/images/FullLogo.png");
        ImageIcon logoIcon = new ImageIcon(logoURL);
        JLabel appLogo = new JLabel(logoIcon);
        appLogo.setHorizontalAlignment(SwingConstants.LEFT);
        add(appLogo, BorderLayout.WEST);                                                                          //-Adds the application logo to the panel

    //-2. Welcome Note and Date/Time
        welcomeNote = StyleHelper.createLabel(
                                                "Welcome to Property Base", 
                                                Font.CENTER_BASELINE, 
                                                SwingConstants.CENTER
                                             );
                add(welcomeNote);                                                                   //-Adds the welcome note to the panel
    }


     /**
     * Makes this method labels to be accessible from other classes
     * Label updates can be safely done from other classes
     * @return
     */
    ///public JLabel getLogo() { return appLogo; }
    public JLabel getWelcome() { return welcomeNote; }
    public JLabel getDateTime() { return dateTime; }
}



/**
 * References
 * OpenAI. (2025, September 02). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */