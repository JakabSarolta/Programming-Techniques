package ro.tuc.pt.gui.admin;

import javax.swing.*;
import java.awt.*;

public class NumberOfTimesReport extends JFrame {
    JTextField orderLimit = new JTextField();

    public NumberOfTimesReport(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){
        JPanel contentPanel = new JPanel();
        JLabel limit = new JLabel("Order limit:");
        JButton generate = new JButton("GENERATE");

        this.setSize(400, 300);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(limit, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(orderLimit, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        generate.setActionCommand("generate2");
        generate.addActionListener(AdministratorView.administratorController);
        contentPanel.add(generate, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getOrderLimit() {
        return orderLimit.getText();
    }

}
