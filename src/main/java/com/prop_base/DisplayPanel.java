package com.prop_base;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
    /**
     * Constructor for the DisplayPanel class.
     */
    public DisplayPanel()
    {
        setLayout(new GridBagLayout());
        setBackground(StyleHelper.CONTENT_BG);
    }
    
    /**
     * Method is called to display a new screen in the display panel when buttons are pressed
     * @param screen
     */
    public void showScreen(JPanel screen) 
    {
        removeAll();                                                                                //-Clears the panel
        add(screen, new GridBagConstraints());                                                      //-Adds new screen
        revalidate();                                                                               //-Revalidates the panel
        repaint();                                                                                  //-Repaints the panel
    }
}
