package com.prop_base;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
 

public class StyleHelper 
{

//-Application Theme colors
    public static final Color DARK_BG = new Color(45, 45, 45);
    public static final Color LOGO_BG = new Color(83, 113, 255);                                    //-RGB for #491470 same colour as logo
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color TOP_PANEL_GRADIENT_TOP = new Color(75, 0, 130);
    public static final Color TOP_PANEL_GRADIENT_BOTTOM = new Color(45, 45, 45);
//-Application Button colors
    private static final Color BTN_BG = new Color(45, 45, 45);                                      //-Dark Gray
    private static final Color BTN_FG = Color.WHITE;
    private static final Color BTN_HOVER = new Color(70, 130, 180);                                 //-Steel blue
    private static final Color BTN_CLICK = new Color(30, 144, 255);                                 //-Dodger blue

//-Application Font constants
    private static final String FONT_FAMILY = "Arial";
    private static final int FONT_SIZE = 16;

    
    /**
     * Creates a styled JLabel with predefined font and color
     * @param text The label text
     * @param fontStyle The font style (e.g., Font.PLAIN, Font.BOLD)
     * @param alignment The horizontal alignment (e.g., SwingConstants.LEFT)
     * @return A styled JLabel
     * Styling assisted by OpenAI
     */
    public static JLabel createLabel(String text, int fontStyle, int alignment) 
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_FAMILY, fontStyle, FONT_SIZE));
        label.setForeground(TEXT_LIGHT);
        label.setHorizontalAlignment(alignment);
        return label;
    }


    /**
     * Paints a gradient background for the specified panel
     * @param panel
     * @param g The Graphics object used for painting
     */
    public static void paintGradientBackground(JPanel panel, Graphics g) 
    {
        Graphics2D g2D = (Graphics2D) g;                                                            //-Cast Graphics to Graphics2D
        int width = panel.getWidth();                                                               //-Get the width of the panel
        int height = panel.getHeight();                                                             //-Get the height of the panel
        GradientPaint paint = new GradientPaint(
                                                0, 0, TOP_PANEL_GRADIENT_TOP,
                                                0, height, TOP_PANEL_GRADIENT_BOTTOM
                                               );
        g2D.setPaint(paint);                                                                        //-Sets the gradient paint
        g2D.fillRect(0, 0, width, height);                                                          //-Fills the panel with the gradient
    }


    /**
     * Adds padding to a JLabel.
     * @param label The JLabel to which padding will be added
     * @param top The top padding
     * @param left The left padding
     * @param bottom The bottom padding
     * @param right The right padding
     */
    public static void addPadding(JLabel label, int top, int left, int bottom, int right)           //-Adds padding to a JLabel
    {
        Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);                  //-Creates an empty border with the specified padding
        label.setBorder(border);                                                                    //-Sets the border to the label
    }


    /**
     * Adds a hover effect to a JLabel.
     * @param label The JLabel to which the hover effect will be added
     * @param hoverColor The color to apply when the mouse is over the label
     * @param normalColor The color to apply when the mouse is not over the label
     */
    public static void addHoverEffect(JLabel label, Color hoverColor, Color normalColor) 
    {
        label.addMouseListener(new MouseAdapter()                                                   //-MouseAdapter to handle hover events
        {
            @Override
            public void mouseEntered(MouseEvent e)                                                  //-Mouse placed on the object event
            {
                label.setForeground(hoverColor);
            }
                @Override
                public void mouseExited(MouseEvent e)                                               //-Mouse removed from the object event
                {
                    label.setForeground(normalColor);
            }
        });
    }


    /**
     * Adds a dynamic font size to a JLabel based on the size of its parent JPanel.
     * @param panel The JPanel that contains the JLabel
     * @param label The JLabel to which the dynamic font size will be applied
     * @param divisor The divisor used to calculate the font size
     */
    public static void addDynamicFont(JPanel panel, JLabel label, int divisor, int maxSize) 
    {
        panel.addComponentListener(new ComponentAdapter()                                           //-Listen for component resize events
        {
            @Override
            public void componentResized(ComponentEvent e)                                          //-Component resized event
            {
                int newSize = Math.min(panel.getWidth() / divisor, maxSize);                        //-Calculates new font size
                label.setFont(new Font("Arial", Font.BOLD, newSize));                               //-Sets the new font size
            }
        });
    }


    /**
     * Styles a sidebar button with consistent colors, padding, and hover/click effects   
     */
    public static void styleSidebarButton(JButton button) 
    {
        button.setFocusPainted(false);                                                              //-Disable focus painting
        button.setContentAreaFilled(false);                                                         //-Set false to paint custom background
        button.setOpaque(false);                                                                    //-Make base transparent
        button.setForeground(BTN_FG);                                                               //-Set foreground color
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));                                           //-Set font
        button.setHorizontalAlignment(SwingConstants.LEFT);                                         //-Set horizontal alignment
        button.setBorder(BorderFactory.createEmptyBorder());                                        //-Remove default borders entirely
        button.setPreferredSize(new Dimension(180, 40));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setMargin(new Insets(8, 12, 8, 12));                                                 //-Sets padding inside the button
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    //-Custom UI for rounded button
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() 
        {
            @Override                                                                               //-Paint() overrides every styling set outside this method
            public void paint(Graphics g, JComponent c) 
            {
                Graphics2D g2D = (Graphics2D) g.create();                                           //-Creates a copy of the graphics context
                g2D.setRenderingHint(                                                               //-Enables anti-aliasing
                                      RenderingHints.KEY_ANTIALIASING, 
                                      RenderingHints.VALUE_ANTIALIAS_ON
                                    );
                int width = c.getWidth();                                                           //-Gets the width of the component
                int height = c.getHeight();                                                         //-Gets the height of the component
                int radius = height / 2;                                                            //-Gets the radius for rounded corners

            //-Sets a background color (hover/pressed)
                g2D.setColor(button.getModel().isPressed() ? BTN_CLICK :
                            button.getModel().isRollover() ? BTN_HOVER : BTN_BG);
                g2D.fillRoundRect(0, 0, width, height, radius, radius);

            //-Draw the text manually
                g2D.setFont(button.getFont());                                                      //-Sets the font for the button text
                FontMetrics fontMat = g2D.getFontMetrics(button.getFont());                         //-Gets the font metrics for the button text
                String text = button.getText();                                                     //-Gets the button text
                int textWidth = fontMat.stringWidth(text);                                          //-Gets the width of the button text
                int textHeight = fontMat.getAscent();                                               //-Gets the height of the button text
                int x = 20;                                                                         //-Applies left padding
                int y = (height + textHeight) / 2 - 2;                                              //-Centers the text vertically
                g2D.setColor(button.getForeground());                                               //-Sets the text color
                g2D.drawString(text, x, y);                                                         //-Draws the button text
                
                g2D.dispose();                                                                      //-Releases the graphics context
            
            }
        });
        button.addChangeListener(e -> button.repaint());                                            //-Repaints the button when its state changes
    }
}





/**
 * References
 * OpenAI. (2025, September 10). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */