package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.BaseProduct;
import ro.tuc.pt.business_logic.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateCompositeProductController implements ActionListener {
    DeliveryService deliveryService = new DeliveryService();
    private final CreateCompositeProductView createCompositeProductView;

    public CreateCompositeProductController(CreateCompositeProductView createCompositeProductView) {
        this.createCompositeProductView = createCompositeProductView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("create")){
            if (createCompositeProductView.getTitleT().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty parameter!!");
            } else {
                    String title = createCompositeProductView.getTitleT();
                    ArrayList<BaseProduct> products = new ArrayList<>();
                    products.add((BaseProduct) createCompositeProductView.getFirstCourseT());
                    products.add((BaseProduct) createCompositeProductView.getSecondCourseT());
                    deliveryService.createComposedProduct(title, products);
                    AdministratorView.administratorController.updateTable(DeliveryService.menuItemsToString());
                    JOptionPane.showMessageDialog(null, "The composite product has been created!!");
                    createCompositeProductView.dispose();
            }
        }
    }
}
