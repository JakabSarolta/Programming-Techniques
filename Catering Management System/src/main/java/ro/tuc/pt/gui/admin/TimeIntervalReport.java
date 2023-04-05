package ro.tuc.pt.gui.admin;

import javax.swing.*;
import java.awt.*;

public class TimeIntervalReport extends JFrame {
    JTextField startTimeT = new JTextField();
    JTextField endTimeT = new JTextField();

    public TimeIntervalReport(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){
        JPanel contentPanel = new JPanel();
        JLabel startTime = new JLabel("Start hour:");
        JLabel endTime = new JLabel("End hour:");
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
        contentPanel.add(startTime, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(endTime, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(startTimeT, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(endTimeT, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        generate.setActionCommand("generate1");
        generate.addActionListener(AdministratorView.administratorController);
        contentPanel.add(generate, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getStartTimeT() {
        return startTimeT.getText();
    }

    public String getEndTimeT() {
        return endTimeT.getText();
    }
}
