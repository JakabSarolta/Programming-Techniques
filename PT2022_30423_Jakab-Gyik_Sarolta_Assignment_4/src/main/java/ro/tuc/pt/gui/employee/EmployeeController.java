package ro.tuc.pt.gui.employee;

import ro.tuc.pt.business_logic.DeliveryService;
import ro.tuc.pt.business_logic.Order;
import ro.tuc.pt.utils.TableFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EmployeeController implements ActionListener, Observer {
    public final EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        update(null, DeliveryService.orders);
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.addEmployeeObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("done")){
            if(DeliveryService.orders.size() != 0){
                DeliveryService.orders.get(0).setEndDate(LocalDateTime.now());
                DeliveryService.finishedOrders.add(DeliveryService.orders.get(0));
                DeliveryService.orders.remove(0);
                update(null, DeliveryService.orders);
            } else{
                JOptionPane.showMessageDialog(null, "No orders to be prepared!!");
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        DefaultTableModel model = TableFactory.createTableFromHashMap((ArrayList<Order>) arg);
        employeeView.table.setModel(model);
    }
}
