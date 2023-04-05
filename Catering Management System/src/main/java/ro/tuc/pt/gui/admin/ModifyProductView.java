package ro.tuc.pt.gui.admin;

import javax.swing.*;
import java.awt.*;

public class ModifyProductView extends JFrame {
    JPanel contentPanel = new JPanel();
    JLabel title = new JLabel("Title:");
    JTextField titleT = new JTextField();
    JLabel rating = new JLabel("Rating:");
    JTextField ratingT = new JTextField();
    JLabel calories = new JLabel("Calories:");
    JTextField caloriesT = new JTextField();
    JLabel proteins = new JLabel("Proteins:");
    JTextField proteinsT = new JTextField();
    JLabel fats = new JLabel("Fats:");
    JTextField fatsT = new JTextField();
    JLabel sodium = new JLabel("Sodium:");
    JTextField sodiumT = new JTextField();
    JLabel price = new JLabel("Price:");
    JTextField priceT = new JTextField();
    JButton modify = new JButton("modify");

    ModifyProductController modifyProductController = new ModifyProductController(this);
    public ModifyProductView(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){
        this.setSize(400, 300);
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
        contentPanel.add(rating, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(ratingT, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(calories, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(caloriesT, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(proteins, c);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(proteinsT, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(fats, c);
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(fatsT, c);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(sodium, c);
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(sodiumT, c);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(price, c);
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(priceT, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        modify.setActionCommand("modify");
        modify.addActionListener(modifyProductController);
        contentPanel.add(modify, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getTitleT() {
        return titleT.getText();
    }

    public String getRatingT() {
        return ratingT.getText();
    }

    public String getCaloriesT() {
        return caloriesT.getText();
    }

    public String getProteinsT() {
        return proteinsT.getText();
    }

    public String getFatsT() {
        return fatsT.getText();
    }

    public String getSodiumT() {
        return sodiumT.getText();
    }

    public String getPriceT() {
        return priceT.getText();
    }
}
