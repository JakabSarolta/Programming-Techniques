package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductController implements ActionListener {
    DeliveryService deliveryService = new DeliveryService();
    private final AddProductView addView;

    public AddProductController(AddProductView addView) {
        this.addView = addView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("addProduct")){
            if (addView.getTitleT().isEmpty() || addView.getPriceT().isEmpty() || addView.getRatingT().isEmpty() || addView.getCaloriesT().isEmpty() || addView.getProteinsT().isEmpty() || addView.getFatsT().isEmpty() || addView.getSodiumT().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty parameter(s)!!");
            } else {
                String title = addView.getTitleT();
                Float rating = Float.parseFloat(addView.getRatingT());
                Integer calories = Integer.parseInt(addView.getCaloriesT());
                Integer proteins = Integer.parseInt(addView.getProteinsT());
                Integer fats = Integer.parseInt(addView.getFatsT());
                Integer sodium = Integer.parseInt(addView.getSodiumT());
                Double price = Double.parseDouble(addView.getPriceT());
                if (rating < 0 || rating > 5.0 || calories <= 0 || proteins < 0 || fats < 0 || sodium < 0 || price <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect parameter(s)!!");
                } else {
                    deliveryService.addProduct(title, rating, calories, proteins, fats, sodium, price);
                    AdministratorView.administratorController.updateTable(DeliveryService.menuItemsToString());
                    addView.dispose();
                }
            }
        }
    }
}
