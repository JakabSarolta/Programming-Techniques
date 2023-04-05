package ro.tuc.pt.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The Controller class implements ActionListener interface and its method actionPerformed is called
 * every time the user selects any of the 3 buttons. This class creates the client, product or order window.
 */
public class Controller implements ActionListener {

    /**
     * The actionPerformed overridden method differentiates the 3 different possible scenarios:
     * if the client button was pressed, then it opens the client window
     * if the product button was pressed, then it opens the product window
     * if the order button was pressed, then it opens the order window
     * @param e the action generated by the pressing of a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Client")){
            new ClientView("Client window");
        } else if(command.equals("Product")){
            new ProductView("Product window");
        } else{
            new OrderView("Order window");
        }
    }
}