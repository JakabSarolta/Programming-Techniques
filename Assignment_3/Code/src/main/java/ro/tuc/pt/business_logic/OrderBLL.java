package ro.tuc.pt.business_logic;

import ro.tuc.pt.data_access.AbstractDAO;
import ro.tuc.pt.data_access.ClientDAO;
import ro.tuc.pt.data_access.OrderDAO;
import ro.tuc.pt.data_access.ProductDAO;
import ro.tuc.pt.model.Client;
import ro.tuc.pt.model.Order;
import ro.tuc.pt.model.Product;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * Order business logic class that calls the methods of the OrderDAO class.
 * This class works with the order table from the database. It is called by the Controller to perform add, delete and update.
 */
public class OrderBLL {
    private AbstractDAO<Order> orderDAO;
    private AbstractDAO<Product> productDAO = new ProductDAO();
    private AbstractDAO<Client> clientDAO = new ClientDAO();
    private BufferedWriter writer;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * The method calls the findById method of the OrderDAO class and verifies if the order was found.
     * In case of error it prints a message to the console.
     * @param id the id of the order we want to find
     * @return the order with the specified id
     */
    public Order findOrderById(int id) {
        Order st = (Order) orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * The method calls the add method of the orderDAO class and verifies if the order was found.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new order and inserts it into the Order table.
     * @param id the id of the order
     * @param idclient the id of the client
     * @param idproduct the id of the product
     * @param amount the number of the products to buy
     * @return true if the insert was completed successfully, and false otherwise
     */
    public boolean add(String id, String idclient, String idproduct, String amount){
        if(id.isEmpty() || idclient.isEmpty() || idproduct.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty parameter!");
            return false;
        }
        if(Integer.parseInt(id) <= 0 || Integer.parseInt(idclient) <= 0 || Integer.parseInt(idproduct) <= 0 || Integer.parseInt(amount) <= 0){
            JOptionPane.showMessageDialog(null, "The parameters cannot be negative!");
            return false;
        }
        if(orderDAO.findById(Integer.parseInt(id)) != null){
            JOptionPane.showMessageDialog(null, "Order with the same id already exists!");
            return false;
        }
        if(clientDAO.findById(Integer.parseInt(idclient)) == null){
            JOptionPane.showMessageDialog(null, "Client not found!");
            return false;
        }
        if(productDAO.findById(Integer.parseInt(idproduct)) == null){
            JOptionPane.showMessageDialog(null, "Product not found!");
            return false;
        }
        Client client = clientDAO.findById(Integer.parseInt(idclient));
        Order order = new Order(Integer.parseInt(id), Integer.parseInt(idclient), Integer.parseInt(idproduct), Integer.parseInt(amount));
        Product p = productDAO.findById(Integer.parseInt(idproduct)); //stock decreases
        if(p.getStock() - Integer.parseInt(amount) > 0) {
            p.setStock(p.getStock() - Integer.parseInt(amount));
        }
        else{
            p.setStock(0);
        }
        try {
            productDAO.update(p);
            orderDAO.insert(order);
            createBill(client, p, order);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the delete method of the orderDAO class and deletes the order.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new order and deletes it from the Order table.
     * @param id the id of the order
     * @return true if the delete was completed successfully, and false otherwise
     */
    public boolean delete(String id){
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "Id field empty!");
            return false;
        }
        if(Integer.parseInt(id) <= 0){
            JOptionPane.showMessageDialog(null, "Id field cannot be negative!");
            return false;
        }
        Order order = orderDAO.findById(Integer.parseInt(id));
        if(order == null){
            JOptionPane.showMessageDialog(null, "Order not found!");
            return false;
        }
        try {
            orderDAO.delete(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the update method of the orderDAO class and updates the information about the order.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new order instance to provide as parameter to the method
     * and updates its fields in the Order table.
     * @param id the id of the order
     * @param idclient the id of the client
     * @param idproduct the id of the product
     * @param amount the number of products to be ordered
     * @return true if the update was completed successfully, and false otherwise
     */
    public boolean update(String id, String idclient, String idproduct, String amount){
        if(id.isEmpty() || idclient.isEmpty() || idproduct.isEmpty() || amount.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty field!!");
            return false;
        }
        if(Integer.parseInt(id) <= 0 || Integer.parseInt(idclient) <= 0 || Integer.parseInt(idproduct) <= 0 || Integer.parseInt(amount) <= 0){
            JOptionPane.showMessageDialog(null, "The parameters cannot be negative!");
            return false;
        }
        if(clientDAO.findById(Integer.parseInt(idclient)) == null){
            JOptionPane.showMessageDialog(null, "Client not found!");
            return false;
        }
        if(productDAO.findById(Integer.parseInt(idproduct)) == null){
            JOptionPane.showMessageDialog(null, "Product not found!");
            return false;
        }
        Order order = new Order(Integer.parseInt(id), Integer.parseInt(idclient), Integer.parseInt(idproduct), Integer.parseInt(amount));
        Order o = orderDAO.findById(Integer.parseInt(id));
        if(o == null){
            JOptionPane.showMessageDialog(null, "Order not found!");
            return false;
        }
        if(o.getAmount() > order.getAmount()){
            JOptionPane.showMessageDialog(null, "Incorrect amount specified for the order");
            return false;
        }
        Product p = productDAO.findById(Integer.parseInt(idproduct)); //when stock decreases
        if(p.getStock() - (o.getAmount() - Integer.parseInt(amount)) > 0) {
            p.setStock(p.getStock() - Integer.parseInt(amount));
        }
        else{
            p.setStock(0);
        }
        try {
            productDAO.update(p);
            orderDAO.update(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method finds all the rows of the database table Order.
     * @return a list of orders found in the database table
     */
    public List<Order> findALl() {
        List<Order> orders = orderDAO.findAll();
        return orders;
    }

    public void createBill(Client client, Product product, Order order) throws IOException {
        openWriter("Log.txt");
        writer.write("              BON FISCAL");
        writer.write("\n\nClient: " + client.getName());
        writer.write("\nProduct: " + product.getName());
        writer.write("\nAmount: " + order.getAmount());
        writer.write("\nUnit price: " + product.getPrice());
        writer.write("\nTotal: " + String.format("%.2f", product.getPrice()*order.getAmount()));
        closeWriter();
    }

    private void openWriter(String fileName) {
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter(){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
