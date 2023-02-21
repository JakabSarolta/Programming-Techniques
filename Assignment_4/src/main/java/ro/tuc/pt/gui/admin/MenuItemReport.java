package ro.tuc.pt.gui.admin;

import javax.swing.*;
import java.awt.*;

public class MenuItemReport extends JFrame {
    JTextField dayT = new JTextField();

    public MenuItemReport(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){
        JPanel contentPanel = new JPanel();
        JLabel day = new JLabel("Order day:");
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
        contentPanel.add(day, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(dayT, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        generate.setActionCommand("generate4");
        generate.addActionListener(AdministratorView.administratorController);
        contentPanel.add(generate, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getDayT() {
        return dayT.getText();
    }
}
