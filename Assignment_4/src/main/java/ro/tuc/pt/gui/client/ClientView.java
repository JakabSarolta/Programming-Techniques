package ro.tuc.pt.gui.client;

import ro.tuc.pt.business_logic.DeliveryService;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {

    JPanel contentPanel = new JPanel();
    JLabel createOrder = new JLabel("Create Order");
    JButton add = new JButton("ADD");
    JButton create = new JButton("CREATE");
    JLabel product = new JLabel("Product:");
    JComboBox<String> products;
    JLabel quantity = new JLabel("Quantity:");
    JSpinner quantityS = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    JLabel order = new JLabel("");

    JLabel searchBy = new JLabel("Search by");
    JLabel keyword = new JLabel("Keyword:");
    JTextField keywordT = new JTextField();
    JTextField ratingT = new JTextField();
    JTextField caloriesT = new JTextField();
    JTextField proteinsT = new JTextField();
    JTextField fatsT = new JTextField();
    JTextField sodiumT = new JTextField();
    JTextField priceT = new JTextField();
    JLabel rating = new JLabel("Rating:");
    JLabel calories = new JLabel("Calories:");
    JLabel proteins = new JLabel("Proteins:");
    JLabel fats = new JLabel("Fats:");
    JLabel sodium = new JLabel("Sodium:");
    JLabel price = new JLabel("Price:");



    JButton search = new JButton("SEARCH");

    public JTable table = new JTable();
    ClientController clientController = new ClientController(this);

    public ClientView(String title) throws HeadlessException {
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
        c.gridwidth = 2;
        contentPanel.add(searchBy, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(keyword, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(keywordT, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(rating, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(ratingT, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(calories, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(caloriesT, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(proteins, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(proteinsT, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(fats, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(fatsT, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(sodium, c);

        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(sodiumT, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(price, c);

        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(priceT, c);

        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        quantityS.setValue(1);
        search.setActionCommand("search");
        search.addActionListener(clientController);
        contentPanel.add(search, c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(createOrder, c);

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(product, c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        products = new JComboBox<>(DeliveryService.menuItemsToStrings());
        contentPanel.add(products, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(quantity, c);

        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(quantityS, c);

        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        add.setActionCommand("add");
        add.addActionListener(clientController);
        contentPanel.add(add, c);

        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        create.setActionCommand("create");
        create.addActionListener(clientController);
        contentPanel.add(create, c);

        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.fill = GridBagConstraints.CENTER;
        contentPanel.add(order, c);

        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.BOTH;
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        contentPanel.add(new JScrollPane(table), c);

        this.setContentPane(contentPanel);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Integer getProducts() {
        return products.getSelectedIndex();
    }

    public Integer getQuantityS() {
        return (Integer)quantityS.getValue();
    }

    public void setOrder(String text) {
        this.order.setText(text);
    }

    public String getOrder() {
        return order.getText();
    }

    public String getKeywordT() {
        if(keywordT.getText().isEmpty()){
            return "";
        }
        return keywordT.getText();
    }

    public String getRatingT() {
        if(ratingT.getText().isEmpty()){
            return "";
        }
        return ratingT.getText();
    }

    public String getCaloriesT() {
        if(calories.getText().isEmpty()){
            return "";
        }
        return caloriesT.getText();
    }

    public String getProteinsT() {
        if(proteinsT.getText().isEmpty()){
            return "";
        }
        return proteinsT.getText();
    }

    public String getFatsT() {
        if(fatsT.getText().isEmpty()){
            return "";
        }
        return fatsT.getText();
    }

    public String getSodiumT() {
        if(sodiumT.getText().isEmpty()){
            return "";
        }
        return sodiumT.getText();
    }

    public String getPriceT() {
        if(priceT.getText().isEmpty()){
            return "";
        }
        return priceT.getText();
    }
}
