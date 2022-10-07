package ro.tuc.pt.gui.admin;

import ro.tuc.pt.business_logic.BaseProduct;
import ro.tuc.pt.business_logic.DeliveryService;
import ro.tuc.pt.business_logic.MenuItem;

import javax.swing.*;
import java.awt.*;

public class CreateCompositeProductView extends JFrame {
    JPanel contentPanel = new JPanel();
    JLabel title = new JLabel("Title:");
    JTextField titleT = new JTextField();
    JLabel firstCourse = new JLabel("First course:");
    JComboBox firstCourseT;
    JLabel secondCourse = new JLabel("Second course:");
    JComboBox secondCourseT;
    JButton create = new JButton("create");

    CreateCompositeProductController createCompositeProductController = new CreateCompositeProductController(this);
    public CreateCompositeProductView(String title) throws HeadlessException {
        super(title);
        prepareGui();
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
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(title, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(titleT, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(firstCourse, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        String[] args = DeliveryService.menuItemsToStrings();
        firstCourseT = new JComboBox(args);
        secondCourseT = new JComboBox(args);
        contentPanel.add(firstCourseT, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(secondCourse, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(secondCourseT, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        create.setActionCommand("create");
        create.addActionListener(createCompositeProductController);
        contentPanel.add(create, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getTitleT() {
        return titleT.getText();
    }

    public MenuItem getFirstCourseT() {
        return DeliveryService.menuItems.get(firstCourseT.getSelectedIndex());
    }

    public MenuItem getSecondCourseT() {
        return DeliveryService.menuItems.get(secondCourseT.getSelectedIndex());
    }
}
