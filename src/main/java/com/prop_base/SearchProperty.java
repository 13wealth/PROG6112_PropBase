package com.prop_base;

import java.awt.BorderLayout;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchProperty extends JPanel {

    private final SearchPropertyCard card;

    public SearchProperty() {
        setLayout(new BorderLayout());
        card = new SearchPropertyCard();
        add(card, BorderLayout.CENTER);

        // Hook button logic
        card.getSearchButton().addActionListener(e -> {
            String query = card.getSearchQuery();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter an Account Number.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            JSONObject property = searchPropertyByAccountNumber(query);
            updateResultsTable(property);
        });
    }

    private JSONObject searchPropertyByAccountNumber(String accountNumber) {
        try {
            File file = new File("properties.json");
            if (!file.exists()) return null;

            String content = new String(Files.readAllBytes(Paths.get("properties.json")));
            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if (accountNumber.equals(obj.optString("Account Number"))) {
                    return obj;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void updateResultsTable(JSONObject property) {
        String[] columns = {
            "Property Address", "Monthly Rent", "Type",
            "Bedrooms", "Bathrooms", "Status", "Account Number"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (property != null) {
            Object[] row = {
                property.optString("Property Address"),
                property.optString("Monthly Rent"),
                property.optString("Property Type"),
                property.optString("Bedrooms"),
                property.optString("Bathrooms"),
                property.optString("Status"),
                property.optString("Account Number")
            };
            model.addRow(row);
        }

        card.getResultsTable().setModel(model);
    }
}
