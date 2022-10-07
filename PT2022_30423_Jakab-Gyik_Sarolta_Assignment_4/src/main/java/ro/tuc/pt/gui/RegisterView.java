package ro.tuc.pt.gui;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    JPanel contentPanel = new JPanel();
    JButton register = new JButton("REGISTER");
    JLabel title = new JLabel("Register");
    JLabel username = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JLabel status = new JLabel("Status:");
    String[] st = {"ADMIN", "CLIENT", "EMPLOYEE"};
    JComboBox<String> states = new JComboBox<>(st);
    JTextField userName = new JTextField();
    JPasswordField passWord = new JPasswordField();

    RegisterController registerController = new RegisterController(this);
    public RegisterView(String title) throws HeadlessException {
        super(title);
        prepareGui();
    }

    public void prepareGui(){
        this.setSize(400, 300);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        contentPanel.add(title, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPanel.add(username, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPanel.add(userName, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        contentPanel.add(password, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        passWord.setEchoChar('*');
        contentPanel.add(passWord, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPanel.add(status, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPanel.add(states, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        register.setActionCommand("register");
        register.addActionListener(registerController);
        contentPanel.add(register, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
    }

    public Integer getStates() {
        return states.getSelectedIndex();
    }

    public String getUserName() {
        return userName.getText();
    }

    public String getPassWord() {
        return String.valueOf(passWord.getPassword());
    }
}
