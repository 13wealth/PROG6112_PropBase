package com.prop_base;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
    /**
     * Constructor for the DisplayPanel class
     */
    public DisplayPanel()
    {
        setLayout(new GridBagLayout());
        setBackground(StyleHelper.CONTENT_BG);                                                      //-Sets background color for the content panel
    }
    
    /**
     * Method is called to display a form to capture new property
     * @param screen
     */
    public void addScreen(JPanel screen) 
    {
        removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.NONE;                                                     //-Sets the panel to not stretch
            gbc.anchor = GridBagConstraints.CENTER;                                                 //-Centers the panel
        add(screen, gbc);                                                                           //-Adds the screen panel to the display panel
        revalidate();                                                                               //-Revalidates the panel
        repaint();                                                                                  //-Repaints the panel
    }
}
