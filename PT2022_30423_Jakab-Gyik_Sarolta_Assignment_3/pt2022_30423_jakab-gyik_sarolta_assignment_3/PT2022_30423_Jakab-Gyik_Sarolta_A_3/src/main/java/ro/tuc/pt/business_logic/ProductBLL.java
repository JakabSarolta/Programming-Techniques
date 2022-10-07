package ro.tuc.pt.business_logic;

import ro.tuc.pt.data_access.AbstractDAO;
import ro.tuc.pt.data_access.ProductDAO;
import ro.tuc.pt.model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * Product business logic class that calls the methods of the ProductDAO class.
 * This class works with the Product table from the database. It is called by the Controller to perform add, delete and update.
 */
public class ProductBLL {
    private AbstractDAO<Product> productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * The method calls the findById method of the ProductDAO class and verifies if the product was found.
     * In case of error it prints a message to the console.
     * @param id the id of the product we want to find
     * @return the product with the specified id
     */
    public Product findProductById(int id) {
        Product st = (Product)productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * The method calls the add method of the productDAO class and verifies if the product was found.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new product and inserts it into the Product table.
     * @param id the id of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock available from that product
     * @return true if the insert was completed successfully, and false otherwise
     */
    public boolean add(String id, String name, String price, String stock){
        if(id.isEmpty() || name.isEmpty() || price.isEmpty() || stock.isEmpty())
            return false;
        if(Integer.parseInt(id) <= 0 || Float.parseFloat(price) <= 0 || Integer.parseInt(stock) <= 0){
            JOptionPane.showMessageDialog(null, "Parameters cannot be negative!");
            return false;
        }
        if(productDAO.findById(Integer.parseInt(id)) != null){
            JOptionPane.showMessageDialog(null, "Id already found!");
            return false;
        }
       Product product = new Product(Integer.parseInt(id), name, Float.parseFloat(price), Integer.parseInt(stock));
        try {
            productDAO.insert(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the delete method of the productDAO class and deletes the product.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new product instance and deletes it from the Product table.
     * @param id the id of the product
     * @return true if the delete was completed successfully, and false otherwise
     */
    public boolean delete(String id){
        if(id.isEmpty()){
            return false;
        }
        if(Integer.parseInt(id) <= 0){
            JOptionPane.showMessageDialog(null, "Id cannot be negative!");
            return false;
        }
        Product product = productDAO.findById(Integer.parseInt(id));
        if(product == null){
            JOptionPane.showMessageDialog(null, "Product not found!");
            return false;
        }
        try {
            productDAO.delete(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the update method of the productDAO class and updates the information about the product.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new product instance to provide as parameter for the method call
     * and updates its fields in the Product table.
     * @param id the id of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the number of products available
     * @return true if the update was completed successfully, and false otherwise
     */
    public boolean update(String id, String name, String price, String stock){
        if(id.isEmpty() || name.isEmpty() || price.isEmpty() || stock.isEmpty())
            return false;
        if(Integer.parseInt(id) <= 0 || Float.parseFloat(price) <= 0 || Integer.parseInt(stock) <= 0){
            JOptionPane.showMessageDialog(null, "Parameters cannot be negative!");
            return false;
        }
        if(productDAO.findById(Integer.parseInt(id)) == null){
            JOptionPane.showMessageDialog(null, "Product not found!");
            return false;
        }
        Product product = new Product(Integer.parseInt(id), name, Float.parseFloat(price), Integer.parseInt(stock));
        try {
            productDAO.update(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method finds all the rows of the database table Product.
     * @return a list of products found in the database table
     */
    public List<Product> findALl() {
        List<Product> products = productDAO.findAll();
        return products;
    }
}
