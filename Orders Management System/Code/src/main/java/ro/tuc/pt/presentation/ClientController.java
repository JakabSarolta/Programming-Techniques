package ro.tuc.pt.presentation;

import ro.tuc.pt.business_logic.ClientBLL;
import ro.tuc.pt.utils.TableFactory;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The ClientController class implements ActionListener interface and its method actionPerformed is called
 * every time the user selects any of the operations insert, delete or update.
 * This class calls the ClientBLL class to perform the queries.
 */
public class ClientController implements ActionListener {
    private final ClientView clientView;
    private final ClientBLL clientBLL = new ClientBLL();
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        updateTable();
    }

    /**
     * The actionPerformed overridden method calls the add, delete and update methods to execute the
     * queries on the tables of the database when the corresponding buttons have been pushed by the user.
     * @param e the action generated by the pressing of a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("add")){
            clientBLL.add(clientView.getIdText(), clientView.getNameText(), clientView.getAddressText(), clientView.getEmailText());
        } else if(command.equals("delete")){
            clientBLL.delete(clientView.getIdText());
        } else if(command.equals("update")){
            clientBLL.update(clientView.getIdText(), clientView.getNameText(), clientView.getAddressText(), clientView.getEmailText());
        }
        updateTable();
    }

    /**
     * After every query executed, the updateTable method regenerates the table for the gui to be updated
     */
    public void updateTable(){
        DefaultTableModel model = TableFactory.createTable(clientBLL.findALl());
        clientView.table.setModel(model);
    }
}
