package ro.tuc.pt.gui.employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeView extends JFrame{
    JTable table = new JTable();
    EmployeeController employeeController = new EmployeeController(this);

    public EmployeeView(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){

        JPanel contentPanel = new JPanel();
        JButton done = new JButton("ORDER DONE");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        done.setActionCommand("done");
        done.addActionListener(employeeController);
        contentPanel.add(done, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        contentPanel.add(table, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
    }
}
