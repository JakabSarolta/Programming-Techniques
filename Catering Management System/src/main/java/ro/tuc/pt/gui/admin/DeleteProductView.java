package ro.tuc.pt.gui.admin;

import javax.swing.*;
import java.awt.*;

public class DeleteProductView extends JFrame {
    JPanel contentPanel = new JPanel();
    JLabel title = new JLabel("Title:");
    JTextField titleT = new JTextField();
    JButton delete = new JButton("delete");

    DeleteProductController deleteProductController = new DeleteProductController(this);
    public DeleteProductView(String title) throws HeadlessException {
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
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(titleT, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        delete.setActionCommand("delete");
        delete.addActionListener(deleteProductController);
        contentPanel.add(delete, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getTitleT() {
        return titleT.getText();
    }
   }
