package com.prop_base;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValidationsHelper {

    private static final JTextField launchField = new JTextField();
    private static final JTextField nameField = new JTextField();
    private static final JTextField surnameField = new JTextField();

    private static String name;
    private static String surname;

    /**
     * Shows a startup panel asking for:
     * - Choice to launch (1 = launch, else exit)
     * - Name
     * - Surname
     * Returns a String array: {name, surname} if launched
     * Exits if cancelled or choice != 1
     */
    public static String[] startMenu()                                                              //-NOTE: Cannot create a new object for JTextField because variables are final
    {
        launchField.setText("");                                                                    //-Ensures that the fields clears only once, before the loop starts
        nameField.setText("");
        surnameField.setText("");

        while (true) 
        {
            JPanel menuPanel = new JPanel(new GridLayout(0, 1));                                    //-Creates a vertical panel for menu items
            menuPanel.add(new JLabel("<html>Enter '1' to Launch the Application.<br>" +
                                     "Enter any other key to exit.</html>"));
        //-Adds the menu items (Labels and Fields)
            menuPanel.add(new JLabel("Choice:"));
            menuPanel.add(launchField);

            menuPanel.add(new JLabel("First Name:"));
            menuPanel.add(nameField);

            menuPanel.add(new JLabel("Surname:"));
            menuPanel.add(surnameField);

            int result = JOptionPane.showConfirmDialog(
                                                        null,
                                                        menuPanel,
                                                        "PROPBASE STARTUP",
                                                        JOptionPane.OK_CANCEL_OPTION,
                                                        JOptionPane.PLAIN_MESSAGE
                                                      );
            if (result == JOptionPane.OK_OPTION)                                                    //-If user clicked OK
            {
                String menuChoice = launchField.getText().trim();                                   //-Trim and get user input for menu choice
                name = nameField.getText().trim();                                                  //-Trim and get user input for name
                surname = surnameField.getText().trim();                                            //-Trim and get user input for surname

                if (menuChoice.isEmpty() || name.isEmpty() || surname.isEmpty())                    //-Checks if any field is empty
                {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                    continue;                                                                       //-Restarts the loop if validation fails
                }

                    if (!"1".equals(menuChoice))                                                    //-If input is not "1"
                    {
                        JOptionPane.showMessageDialog(null, "Goodbye 👋");
                        System.exit(0);
                    }

                        return new String[]{name, surname};                                         //-Success → return name + surname

                } else {
                    JOptionPane.showMessageDialog(null, "Goodbye 👋");                              //-If user cancels or close application
                    System.exit(0);                                                                 //-Ensures application terminates on close
            }
        }
    }


    /**
     * Getters to be accessed at HomePanel to return name and surname
     * @return
     */
    public static String getName() { return name; }
    public static String getSurname() { return surname; }
}
