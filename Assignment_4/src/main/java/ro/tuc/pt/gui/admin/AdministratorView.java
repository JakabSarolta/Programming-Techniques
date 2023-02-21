package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.DeliveryService;

import javax.swing.*;
import java.awt.*;

public class AdministratorView extends JFrame {
    JPanel contentPanel = new JPanel();
    JButton importProduct = new JButton("IMPORT");
    JButton add = new JButton("add");
    JButton delete = new JButton("delete");
    JButton modify = new JButton("modify");
    JButton create = new JButton("create");
    JLabel report = new JLabel("Report:");
    String[] options = {"products ordered more than a specified number of times", "time interval of the orders", "clients that have ordered more than a specified number of times so far and the\n" +
            "value of the order was higher than a specified amount", "products ordered within a specified day with the number of times they have \n" +
            "been ordered"};
    JComboBox reportChooser = new JComboBox(options);
    JButton generate = new JButton("GENERATE");
    //JButton update = new JButton("update");

    public JTable table = new JTable();
    public static AdministratorController administratorController;

    public AdministratorView(String title) throws HeadlessException {
        super(title);
        administratorController = new AdministratorController(this);
        prepareGui();
        administratorController.updateTable(DeliveryService.menuItemsToString());
    }

    public void prepareGui(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        importProduct.setActionCommand("import");
        importProduct.addActionListener(administratorController);
        contentPanel.add(importProduct, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        add.setActionCommand("add");
        add.addActionListener(administratorController);
        contentPanel.add(add, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        delete.setActionCommand("delete");
        delete.addActionListener(administratorController);
        contentPanel.add(delete, c);
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        modify.setActionCommand("modify");
        modify.addActionListener(administratorController);
        contentPanel.add(modify, c);
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        create.setActionCommand("create");
        create.addActionListener(administratorController);
        contentPanel.add(create, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(report, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.WEST;

        contentPanel.add(reportChooser, c);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        generate.setActionCommand("generate");
        generate.addActionListener(administratorController);
        contentPanel.add(generate, c);
        /*c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        update.setActionCommand("update");
        update.addActionListener(administratorController);
        contentPanel.add(update, c);*/
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.BOTH;
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        contentPanel.add(new JScrollPane(table), c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
    }

    public Integer getReportChooser() {
        return reportChooser.getSelectedIndex();
    }
}
