package com.prop_base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchPropertyCard extends JPanel {

    private final int cornerRadius = 20;
    private final Color bgColor = Color.WHITE;

    private final JButton searchBtn;
    private final JTextField searchField;
    private final JTable resultsTable;

    public SearchPropertyCard() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setPreferredSize(new Dimension(700, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int row = 0;

        // Title
        JLabel title = new JLabel("Search Property", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        add(title, gbc);

        row++;
        gbc.gridwidth = 1;

        // Search label
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Account Number:"), gbc);

        // Search field
        searchField = new JTextField(15);
        gbc.gridx = 1;
        add(searchField, gbc);

        row++;

        // Search button
        searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(0, 102, 204)); // nice blue
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(searchBtn, gbc);

        row++;

        // Results table
        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1; // allows table to expand vertically
        add(scrollPane, gbc);
    }

    // Getter for search button
    public JButton getSearchButton() {
        return searchBtn;
    }

    // Getter for search text
    public String getSearchQuery() {
        return searchField.getText().trim();
    }

    // Getter for results table
    public JTable getResultsTable() {
        return resultsTable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rounded card background
        StyleHelper.paintCard(g, getWidth(), getHeight(), cornerRadius, 0, Color.LIGHT_GRAY, bgColor);
    }
}
