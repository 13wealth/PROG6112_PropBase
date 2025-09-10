package com.prop_base;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StyleHelper 
{

//-Application Theme colors
    public static final Color DARK_BG = new Color(45, 45, 45);
    public static final Color TEXT_LIGHT = Color.LIGHT_GRAY;

//-Application Font constants (easier to manage later)
    private static final String FONT_FAMILY = "Arial";
    private static final int FONT_SIZE = 16;

    /**
     * Creates a styled JLabel with predefined font and color
     * @param text The label text
     * @param fontStyle The font style (e.g., Font.PLAIN, Font.BOLD)
     * @param alignment The horizontal alignment (e.g., SwingConstants.LEFT)
     * @return A styled JLabel
     */
    public static JLabel createLabel(String text, int fontStyle, int alignment) 
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_FAMILY, fontStyle, FONT_SIZE));
        label.setForeground(TEXT_LIGHT);
        label.setHorizontalAlignment(alignment);
        return label;
    }
}
