package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.DeliveryService;
import ro.tuc.pt.data_access.FileWriter;
import ro.tuc.pt.utils.TableFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorController implements ActionListener {
    public AdministratorView administratorView = null;
    DeliveryService deliveryService = new DeliveryService();
    FileWriter fileWriter = new FileWriter();
    TimeIntervalReport timeIntervalReport;
    NumberOfTimesReport numberOfTimesReport;
    ClientReport clientReport;
    MenuItemReport menuItemReport;

    public AdministratorController(AdministratorView administratorView) {
        this.administratorView = administratorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "import":
                deliveryService.importProducts("products.csv");
                break;
            case "add":
                AddProductView addProduct = new AddProductView("ADD PRODUCT");
                break;
            case "delete":
                DeleteProductView deleteProductView = new DeleteProductView("DELETE PRODUCT");
                break;
            case "modify":
                ModifyProductView modifyProductView = new ModifyProductView("MODIFY PRODUCT");
                break;
            case "create":
                CreateCompositeProductView createCompositeProductView = new CreateCompositeProductView("CREATE COMPOSED PRODUCT");
                break;
            case "generate":
                int chosen = administratorView.getReportChooser();
                if(chosen ==0){
                    numberOfTimesReport = new NumberOfTimesReport("REPORT");
                } else if(chosen == 1){
                        timeIntervalReport = new TimeIntervalReport("REPORT");
                } else if(chosen == 2){
                    clientReport = new ClientReport("REPORT");
                } else{
                    menuItemReport = new MenuItemReport("REPORT");
                }
                break;
            case "generate1":
                if(Integer.parseInt(timeIntervalReport.getStartTimeT()) < 0 || Integer.parseInt(timeIntervalReport.getStartTimeT()) > 24 || Integer.parseInt(timeIntervalReport.getEndTimeT()) > 24 || Integer.parseInt(timeIntervalReport.getEndTimeT()) < 0 || (Integer.parseInt(timeIntervalReport.getEndTimeT()) < Integer.parseInt(timeIntervalReport.getStartTimeT()))){
                    JOptionPane.showMessageDialog(null, "The parameters are incorrect!!");
                } else{
                    fileWriter.createTimeIntervalReport(Integer.parseInt(timeIntervalReport.getStartTimeT()), Integer.parseInt(timeIntervalReport.getEndTimeT()), deliveryService.generateTimeIntervalReport(Integer.parseInt(timeIntervalReport.getStartTimeT()), Integer.parseInt(timeIntervalReport.getEndTimeT())));
                    JOptionPane.showMessageDialog(null, "The report has been generated!!");
                    timeIntervalReport.dispose();
                }
                break;
            case "generate2":
                if(Integer.parseInt(numberOfTimesReport.getOrderLimit()) <= 0){
                    JOptionPane.showMessageDialog(null, "The parameter cannot be negative!!");
                } else{
                    fileWriter.createNumberOfTimesReport(Integer.parseInt(numberOfTimesReport.getOrderLimit()), deliveryService.generateNumberOfTimesReport(Integer.parseInt(numberOfTimesReport.getOrderLimit())));
                    JOptionPane.showMessageDialog(null, "The report has been generated!!");
                    numberOfTimesReport.dispose();
                }
                break;
            case "generate3":
                if(Integer.parseInt(clientReport.getOrderLimitT()) < 0 || Integer.parseInt(clientReport.getOrderValueT()) < 0){
                    JOptionPane.showMessageDialog(null, "The parameters cannot be negative!!");
                } else{
                    fileWriter.createClientReport(Integer.parseInt(clientReport.getOrderLimitT()), Integer.parseInt(clientReport.getOrderValueT()), deliveryService.generateClientReport(Integer.parseInt(clientReport.getOrderLimitT()), Double.parseDouble(clientReport.getOrderValueT())));
                    JOptionPane.showMessageDialog(null, "The report has been generated!!");
                    clientReport.dispose();
                }
                break;
            case "generate4":
                if(Integer.parseInt(menuItemReport.getDayT()) < 0 || Integer.parseInt(menuItemReport.getDayT()) > 365){
                    JOptionPane.showMessageDialog(null, "Invalid day pf the year!!");
                } else{
                    fileWriter.createMenuItemReport(Integer.parseInt(menuItemReport.getDayT()), deliveryService.generateMenuItemReport(Integer.parseInt(menuItemReport.getDayT())));
                    JOptionPane.showMessageDialog(null, "The report has been generated!!");
                    menuItemReport.dispose();
                }
                break;
            case "update":
                updateTable(DeliveryService.menuItemsToString()); //updating the table for gui
        }

    }
    public void updateTable(List<String> list){
        DefaultTableModel model = TableFactory.createTable(list);
        administratorView.table.setModel(model);
    }
}
