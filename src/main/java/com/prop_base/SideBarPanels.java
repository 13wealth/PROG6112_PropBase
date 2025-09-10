package com.prop_base;

public class SideBarPanels 
{
        private final JPanel container;

        public SideBarPanels() 
        {
            container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBackground(StyleHelper.DARK_BG);
        }

        public JPanel getContainer() 
        {
            return container;
        }
}

/*
dateTime = StyleHelper.createLabel(
                                                "", 
                                                Font.PLAIN, 
                                                SwingConstants.RIGHT
                                             );
        add(dateTime);                                                                              //-Adds the date and time label to the panel
        UIHelper.startClock(dateTime);                                                              //-Calls the method that starts the clock to update the date/time label
        
public JLabel getDateTime() { return dateTime; }                                                    
*/                                                            
