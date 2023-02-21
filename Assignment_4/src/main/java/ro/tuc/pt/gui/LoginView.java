package ro.tuc.pt.gui;

import ro.tuc.pt.business_logic.*;
import ro.tuc.pt.business_logic.MenuItem;
import ro.tuc.pt.data_access.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginView extends JFrame {
    Serializator<User> serializator = new Serializator<User>();
    Serializator<MenuItem> serializator2 = new Serializator<MenuItem>();
    Serializator<Order> serializator3 = new Serializator<Order>();

    JPanel contentPanel = new JPanel();
    JButton enter = new JButton("LOGIN");
    JButton register = new JButton("REGISTER");
    JLabel title = new JLabel("Login");
    JLabel username = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JLabel status = new JLabel("Status:");
    String[] st = {"ADMIN", "CLIENT", "EMPLOYEE"};
    JComboBox<String> states = new JComboBox<>(st);
    JTextField userName = new JTextField();
    JPasswordField passWord = new JPasswordField();

    LoginController loginController = new LoginController(this);
    public LoginView(String title) throws HeadlessException {
        super(title);
        UserService.users = serializator.deserialize("users.txt");
        DeliveryService.menuItems = serializator2.deserialize("file.txt");
        DeliveryService.orders = serializator3.deserialize("orders.txt");
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
        enter.setActionCommand("enter");
        enter.addActionListener(loginController);
        contentPanel.add(enter, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        register.setActionCommand("register");
        register.addActionListener(loginController);
        contentPanel.add(register, c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serializator.serialize(UserService.users, "users.txt");
                serializator2.serialize(DeliveryService.menuItems, "file.txt");
                serializator3.serialize(DeliveryService.orders, "orders.txt");
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
