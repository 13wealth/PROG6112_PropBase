package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopPanel extends JPanel
{
    private final JLabel welcomeNote;

    public TopPanel(String name, String surname) 
    {

//-Step 1: Set layout and background for top panel
        setLayout(new GridLayout(1, 3));                                                            //-Panel structure: 10 pixels horizontal and vertical spacing
        setPreferredSize(new Dimension(1000, 100));                                                 //-Sets the preferred size for the top panel

//-Step 2: Add components to the top panel
    //-1. Application Logo
        java.net.URL logoPath = getClass().getResource("/images/FullLogo(200v2).png"); 
        ImageIcon logoIcon = new ImageIcon(logoPath);

        JLabel appLogo = new JLabel(logoIcon);
        appLogo.setHorizontalAlignment(SwingConstants.LEFT);                                        //-Aligns the logo to the left
        add(appLogo);                                                                               //-Adds the application logo to the panel

    //-2. Welcome Note
        welcomeNote = StyleHelper.createLabel(
                                                "Welcome to Property Base", 
                                                Font.CENTER_BASELINE, 
                                                SwingConstants.CENTER
                                             );
        add(welcomeNote);                                                                           //-Adds the welcome note to the panel
    
    //-3. Add user details
        JLabel userName = StyleHelper.createLabel(
                                                    name + " " + surname, 
                                                    Font.BOLD, 
                                                    SwingConstants.RIGHT
                                                  );
        add(userName);                                                                              //-Adds the user name to the panel
        
//-Step 3: Add some styling to the top panel       
        StyleHelper.addPadding(appLogo, 0, 0, 0, 0);                          //-Adds padding to appLogo
        StyleHelper.addPadding(welcomeNote, 0, 5, 0, 5);                      //-Adds padding to welcomeNote
        StyleHelper.addPadding(userName, 0, 0, 0, 15);                        //-Adds padding to userName
        StyleHelper.addHoverEffect(userName, Color.YELLOW, StyleHelper.TEXT_LIGHT);                 //-Adds hover effect to userName
        StyleHelper.addDynamicFont(this, welcomeNote, 50, 24);                      //-Adds dynamic font size to welcomeNote
    }

    @Override                                                                                       //-Overrides paintComponent method from JPanel
    protected void paintComponent(Graphics bg)
    {
        super.paintComponent(bg);                                                                   /* Calls the original paintComponent method from the parent class (like JPanel)
                                                                                                     * super.paintComponent(g); is very important when you’re custom painting a Swing component like a JPanel or JButton
                                                                                                     * It ensures that the component is properly rendered before adding any custom graphics.
                                                                                                     * paintComponent(Graphics g) is the method you override when you want to draw custom stuff
                                                                                                     * You can use the Graphics object (g) to draw shapes, text, and images on the component
                                                                                                     * WHY?
                                                                                                     * - Clears the background – The parent class usually paints the default background
                                                                                                     * - Prepares the component for new drawings – By clearing the background, you ensure that any new graphics you draw will be on a clean slate
                                                                                                    */
        StyleHelper.paintGradientBackground(this, bg);                                              //-Paints the gradient background
    }
}
        