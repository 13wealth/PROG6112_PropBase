package com.prop_base;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterProperty extends JPanel
{
    public EnterProperty() 
    {
        EnterPropertyCard card = new EnterPropertyCard();

        if (card.getSaveButton() != null)                                                           //-If the save button exists
        {
            card.getSaveButton().setBackground(Color.BLUE);                                         //-Sets background color to blue
            card.getSaveButton().setForeground(Color.WHITE);                                        //-Sets text color to white

            card.getSaveButton().addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                   SaveToJSON.saveToJson(card);                                                     //-Calls a method that saves property details to JSON
                }
            });
        }

        add(card);
    }
}
    