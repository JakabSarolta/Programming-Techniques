package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductController implements ActionListener {
    DeliveryService deliveryService = new DeliveryService();
    private final ModifyProductView modifyView;

    public ModifyProductController(ModifyProductView modifyView) {
        this.modifyView = modifyView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("modify")){
            if (modifyView.getTitleT().isEmpty() || modifyView.getPriceT().isEmpty() || modifyView.getRatingT().isEmpty() || modifyView.getCaloriesT().isEmpty() || modifyView.getProteinsT().isEmpty() || modifyView.getFatsT().isEmpty() || modifyView.getSodiumT().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty parameter(s)!!");
            } else {
                String title = modifyView.getTitleT();
                Float rating = Float.parseFloat(modifyView.getRatingT());
                Integer calories = Integer.parseInt(modifyView.getCaloriesT());
                Integer proteins = Integer.parseInt(modifyView.getProteinsT());
                Integer fats = Integer.parseInt(modifyView.getFatsT());
                Integer sodium = Integer.parseInt(modifyView.getSodiumT());
                Double price = Double.parseDouble(modifyView.getPriceT());
                if (rating < 0 || rating > 5.0 || calories <= 0 || proteins < 0 || fats < 0 || sodium < 0 || price <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect parameter(s)!!");
                } else {
                    if(!deliveryService.modifyProduct(title, rating, calories, proteins, fats, sodium, price)){
                        JOptionPane.showMessageDialog(null, "Product not found!!");
                    } else{
                        AdministratorView.administratorController.updateTable(DeliveryService.menuItemsToString());
                        modifyView.dispose();
                    }
                }
            }
        }
    }
}
