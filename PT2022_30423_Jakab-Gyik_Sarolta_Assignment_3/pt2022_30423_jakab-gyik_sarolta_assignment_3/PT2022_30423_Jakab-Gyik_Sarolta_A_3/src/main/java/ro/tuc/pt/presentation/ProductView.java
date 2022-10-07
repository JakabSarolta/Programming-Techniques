package ro.tuc.pt.presentation;

import javax.swing.*;
import java.awt.*;

/**
 *
 * The ProductView class extends JFrame and creates the product window with the product table.
 * From this window the user can select one of the 3 operations: add, delete or update product.
 */
public class ProductView extends JFrame {
    public JTable table = new JTable();
    ProductController productController = new ProductController(this);
    public JPanel contentPane = new JPanel();
    JButton button1 = new JButton("add");
    JButton button2 = new JButton("delete");
    JButton button3 = new JButton("update");
    JLabel id = new JLabel("id:", SwingConstants.CENTER);
    JLabel name = new JLabel("name:", SwingConstants.CENTER);
    JLabel price = new JLabel("price:", SwingConstants.CENTER);
    JLabel stock = new JLabel("stock:", SwingConstants.CENTER);
    JTextField idText = new JTextField();
    JTextField nameText = new JTextField();
    JTextField priceText = new JTextField();
    JTextField stockText = new JTextField();
    Color backgroundColor = new Color(204, 255, 255);
    Color textBoxColor = new Color(102, 255, 255);

    public ProductView(String title) throws HeadlessException {
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
        name.setFont(new Font("SansSerif", Font.PLAIN, 18));
        price.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stock.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button1.setActionCommand("add");
        button1.addActionListener(productController);

        button2.setActionCommand("delete");
        button2.addActionListener(productController);

        button3.setActionCommand("update");
        button3.addActionListener(productController);

        contentPane.add(id);
        contentPane.add(idText);
        contentPane.add(name);
        contentPane.add(nameText);
        contentPane.add(price);
        contentPane.add(priceText);
        contentPane.add(stock);
        contentPane.add(stockText);
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

    public String getIdText() {
        return idText.getText();
    }

    public String getNameText() {
        return nameText.getText();
    }

    public String getPriceText() {
        return priceText.getText();
    }

    public String getStockText() {
        return stockText.getText();
    }
}
