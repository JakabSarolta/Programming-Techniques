package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.DeliveryService;
import ro.tuc.pt.utils.TableFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteProductController implements ActionListener {
    DeliveryService deliveryService = new DeliveryService();
    private final DeleteProductView deleteView;

    public DeleteProductController(DeleteProductView deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("delete")){
            if (deleteView.getTitleT().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty parameter!!");
            } else {
                String title = deleteView.getTitleT();
                if(!deliveryService.deleteProduct(title)){
                    JOptionPane.showMessageDialog(null, "Product not found!!");
                } else{
                    AdministratorView.administratorController.updateTable(DeliveryService.menuItemsToString());
                    deleteView.dispose();
                }
            }
        }
    }
}
