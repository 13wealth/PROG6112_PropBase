package com.prop_base;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
 

public class StyleHelper extends JPanel
{

//-Application Theme colors
    public static final Color DARK_BG = new Color(45, 45, 45);
    public static final Color LOGO_BG = new Color(83, 113, 255);                                    //-RGB for #491470 same colour as logo
    public static final Color CONTENT_BG = new Color(240, 240, 240);                                //-Sets background color for the main content panel
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
     * Paints a gradient background for the specified panel
     * 
     * @param panel The panel to paint
     * @param g     The Graphics object used for painting
     * Styling assisted by OpenAI
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
     * Adds a hover effect to a JLabel.
     * @param label         The JLabel to which the hover effect will be added
     * @param hoverColor    The color to apply when the mouse is over the label
     * @param normalColor   The color to apply when the mouse is not over the label
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
     * Creates a styled JLabel with predefined font and color
     * 
     * @param text      The label text
     * @param fontStyle The font style (e.g., Font.PLAIN, Font.BOLD)
     * @param alignment The horizontal alignment (e.g., SwingConstants.LEFT)
     * @return          A styled JLabel
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
     * Styles a JLabel with optional padding, border, text color, background color, and size.
     * Any parameter can be skipped by passing null or 0.
     *
     * @param label            The JLabel to style
     * @param top              Top padding (0 = none)
     * @param left             Left padding (0 = none)
     * @param bottom           Bottom padding (0 = none)
     * @param right            Right padding (0 = none)
     * @param borderColor      Border color (null = no border)
     * @param borderThickness  Border thickness (0 = no border)
     * @param cornerRadius     Rounded corner radius (0 = square)
     * @param textColor        Text (foreground) color (null = default)
     * @param bgColor          Background color (null = transparent/default)
     * @param prefWidth        Preferred width (0 = ignore)
     * @param prefHeight       Preferred height (0 = ignore)
     * Styling assisted by OpenAI
     */
     
    public static void styleLabel(
                                  JLabel label,
                                  int top, int left, int bottom, int right,
                                  Color borderColor, int borderThickness, int cornerRadius,
                                  Color textColor, Color bgColor,
                                  int prefWidth, int prefHeight
                                  ) 
    {
        Border padding = null; 
        Border border = null;

//-If any of the conditions are not met, the corresponding style will be
    //-Padding validation
        if (top > 0 || left > 0 || bottom > 0 || right > 0)                                         //-Only create padding if any value is greater than 0
        {
            padding = new EmptyBorder(top, left, bottom, right);
        }

    //-Border (square or rounded)
        if (borderColor != null && borderThickness > 0)                                             //-Only create border if color and thickness are valid
        {
            border = new LineBorder(borderColor, borderThickness);                                  //-Square border
           
            /*if (cornerRadius > 0) 
            {
                border = new RoundedBorder(borderColor, borderThickness, cornerRadius);
            } else {
                border = new LineBorder(borderColor, borderThickness);
            }*/
        }

    //-Combine padding + border
        if (border != null && padding != null)                                                      //-Create a compound border if both are present
        {
            label.setBorder(new CompoundBorder(border, padding));
            } else if (border != null) {
                label.setBorder(border);
            } else if (padding != null) {
            label.setBorder(padding);
        }

    //-Text color
        if (textColor != null)                                                                      //-Only set text color if valid
        {
            label.setForeground(textColor);
        }

    //-Background color
        if (bgColor != null)                                                                        //-Only set background color if valid
        {
            label.setOpaque(true);                                                                  //-Needed to show background
            label.setBackground(bgColor);
        }

    //-Preferred size
        if (prefWidth > 0 && prefHeight > 0)                                                        //-Only set preferred size if both dimensions are valid
        {
            label.setPreferredSize(new Dimension(prefWidth, prefHeight));
        }
    }

    
    /**
     * Shortcut method to add padding only to a JLabel
     * 
     * @param label     The JLabel to style
     * @param top       The top padding
     * @param left      The left padding
     * @param bottom    The bottom padding
     * @param right     The right padding
     */
    public static void addPadding(JLabel label, int top, int left, int bottom, int right)           //-Adds padding to a JLabel
    {
        Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);                  //-Creates an empty border with the specified padding
        label.setBorder(border);                                                                    //-Sets the border to the label
    }


    /**
     * Adds a dynamic font size to a JLabel based on the size of its parent JPanel.
     * 
     * @param panel     The JPanel that contains the JLabel
     * @param label     The JLabel to which the dynamic font size will be applied
     * @param divisor   The divisor used to calculate the font size
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
     *
     * @param button The button to style
     */
    public static void styleButton(JButton button) 
    {
        button.setFocusPainted(false);                                                              //-Disable focus painting
        button.setContentAreaFilled(false);                                                         //-Set false to paint custom background
        button.setOpaque(false);                                                                    //-Make base transparent
        button.setForeground(BTN_FG);                                                               //-Set foreground color
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));                                       //-Set font
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


    /**
     * Paints a card-like UI component with rounded corners and a subtle shadow
     * 
     * @param g                  The graphics context
     * @param width              The width of the component
     * @param height             The height of the component
     * @param cornerRadius       The radius of the component's corners
     * @param borderThickness    The thickness of the component's border
     * @param borderColor        The color of the component's border
     * @param bgColor            The background color of the component
     * Styling assisted by Open AI
     */
    public static void paintCard(
                                  Graphics g, 
                                  int width, int height, 
                                  int cornerRadius, int borderThickness, 
                                  Color borderColor, Color bgColor
                                ) 
    {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);   /* Graphics object g2D enables anti-aliasing for smoother edges
                                                                                                     * setRenderingHint → This method tells Java how to render shapes, text, or images. 
                                                                                                        You give it a key (what property you want to change) and a value (how you want it changed).
                                                                                                     * RenderingHints.KEY_ANTIALIASING → This key controls anti-aliasing, which is a technique to reduce jagged edges on shapes or text.
                                                                                                     * RenderingHints.VALUE_ANTIALIAS_ON → This value enables anti-aliasing.
                                                                                                     */
    //-Draws the Border
        g2D.setColor(borderColor);
        g2D.fillRoundRect(
                    0, 0, 
                    width, height, 
                    cornerRadius, cornerRadius);

    //-Sets the background
        g2D.setColor(bgColor);
        g2D.fillRoundRect(
                    borderThickness, borderThickness,
                    width - 2 * borderThickness,
                    height - 2 * borderThickness,
                    cornerRadius - borderThickness,
                    cornerRadius - borderThickness);
        g2D.dispose();                                                                               /*Removes window from the screen
                                                                                                      Then frees up the system resources that the 
                                                                                                      window was using (memory, handles, etc.).
                                                                                                    */
    }
    
    
    /**
     * Highlights the border of a field (JComponent) when it is active (focused)
     * Improves user experience by providing visual feedback
     * @param comp The component to add focus highlighting to
     */
    public static void highlightActive(JComponent comp) 
    {
        comp.addFocusListener(new FocusAdapter() 
        {
            Border original = comp.getBorder();                                                     //-Store the original border
            @Override                                                                               //-When the component gains focus
            public void focusGained(FocusEvent a) 
            {
                comp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));                      //-Change border color to blue
            }
            @Override                                                                               //-When the component loses focus
            public void focusLost(FocusEvent b) 
            {
                comp.setBorder(original);                                                           //-Restore the original border
            }
        });
    }


    /**
    * Creates a label + text field pair in a given row of your layout
    * @param label
    * @param row
    * @return
    * Styling was assisted by Open AI
    */
    public static JTextField addLabeledField(JPanel parent, String label, int row) 
    {
        GridBagConstraints controls = new GridBagConstraints();
        controls.insets = new Insets(8, 8, 8, 8);                                                   //-Set padding for components
        controls.fill = GridBagConstraints.HORIZONTAL;                                              //-Make components stretch horizontally
        controls.weightx = 1;                                                                       //-Allow components to grow horizontally

    //-Label
        controls.gridx = 0;                                                                         //-Places the label in the first column
        controls.gridy = row;                                                                       //-Sets the row for the label
        parent.add(new JLabel(label), controls);                                                    //-Add label to the panel

    //-Field
        JTextField field = new JTextField(20);                                                      //-Create a text field with 20 columns
        field.setBorder(new LineBorder(Color.GRAY, 1, true));                                       //-Full gray border with rounded corners
        field.setBackground(Color.WHITE);
        controls.gridx = 1;                                                                         //-places the text field in the second column
        parent.add(field, controls);

        return field;
    }
}



/**
 * References
 * OpenAI. (2025, September 16). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */