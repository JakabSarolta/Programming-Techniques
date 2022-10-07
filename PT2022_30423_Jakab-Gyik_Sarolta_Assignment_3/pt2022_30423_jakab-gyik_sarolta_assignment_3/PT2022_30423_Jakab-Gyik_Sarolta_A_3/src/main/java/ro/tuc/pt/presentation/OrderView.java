package ro.tuc.pt.presentation;

import javax.swing.*;
import java.awt.*;

/**
 *
 * The OrderView class extends JFrame and creates the order window with the order table.
 * From this window the user can select one of the 3 operations: add, delete or update order.
 */
public class OrderView extends JFrame{
    public JTable table = new JTable();
    OrderController orderController = new OrderController(this);
    public JPanel contentPane = new JPanel();
    JButton button1 = new JButton("add");
    JButton button2 = new JButton("delete");
    JButton button3 = new JButton("update");
    JLabel id = new JLabel("id:", SwingConstants.CENTER);
    JLabel idclient = new JLabel("idclient:", SwingConstants.CENTER);
    JLabel idproduct = new JLabel("idproduct:", SwingConstants.CENTER);
    JLabel amount = new JLabel("amount:", SwingConstants.CENTER);
    JTextField orderText = new JTextField();
    JTextField clientText = new JTextField();
    JTextField productText = new JTextField();
    JTextField amountText = new JTextField();
    Color backgroundColor = new Color(204, 255, 255);
    Color textBoxColor = new Color(102, 255, 255);

    public OrderView(String title) throws HeadlessException {
        super(title);
        this.prepareGui();
    }
    public void prepareGui(){
        this.setSize(900, 700);
        contentPane.setLayout(new GridLayout(7, 2, 10, 20));
        button1.setBackground(textBoxColor);
        button2.setBackground(textBoxColor);
        button3.setBackground(textBoxColor);
        button1.setBorder(BorderFactory.createMatteBorder(20, 120, 20, 120, backgroundColor));
        button2.setBorder(BorderFactory.createMatteBorder(20, 120, 20, 120, backgroundColor));
        button3.setBorder(BorderFactory.createMatteBorder(20, 120, 20, 120, backgroundColor));
        id.setFont(new Font("SansSerif", Font.PLAIN, 18));
        idclient.setFont(new Font("SansSerif", Font.PLAIN, 18));
        idproduct.setFont(new Font("SansSerif", Font.PLAIN, 18));
        amount.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button1.setActionCommand("add");
        button1.addActionListener(orderController);

        button2.setActionCommand("delete");
        button2.addActionListener(orderController);

        button3.setActionCommand("update");
        button3.addActionListener(orderController);

        contentPane.add(id);
        contentPane.add(orderText);
        contentPane.add(idclient);
        contentPane.add(clientText);
        contentPane.add(idproduct);
        contentPane.add(productText);
        contentPane.add(amount);
        contentPane.add(amountText);
        contentPane.add(button1);
        contentPane.add(table);
        contentPane.add(button2);
        contentPane.add(new JLabel(""));
        contentPane.add(button3);
        contentPane.add(new JLabel(""));



        this.contentPane.setBackground(backgroundColor);
        this.setContentPane(this.contentPane);
        this.setVisible(true);
    }

    public String getOrderText() {
        return orderText.getText();
    }

    public String getClientText() {
        return clientText.getText();
    }

    public String getProductText() {
        return productText.getText();
    }

    public String getAmountText() {
        return amountText.getText();
    }
}
