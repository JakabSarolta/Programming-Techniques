package ro.tuc.pt.business_logic;

import ro.tuc.pt.data_access.AbstractDAO;
import ro.tuc.pt.data_access.ClientDAO;
import ro.tuc.pt.model.Client;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * Client business logic class that calls the methods of the ClientDAO class.
 * This class works with the Client table from the database. It is called by the Controller to perform add, delete and update.
 */
public class ClientBLL {
    private AbstractDAO<Client> clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * The method calls the findById method of the clientDAO class and verifies if the client was found.
     * In case of error it prints a message to the console.
     * @param id the id of the client we want to find
     * @return the client with the specified id
     */
    public Client findClientById(int id) {
        Client st = (Client)clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * The method calls the add method of the clientDAO class and verifies if the client was found.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new client and inserts it into the Client table.
     * @param id the id of the client
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     * @return true if the insert was completed successfully, and false otherwise
     */
    public boolean add(String id, String name, String address, String email){
        if(id.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty parameters!!");
            return false;
        }
        if(Integer.parseInt(id) <= 0){
            JOptionPane.showMessageDialog(null, "Id cannot be negative!");
            return false;
        }
        if(clientDAO.findById(Integer.parseInt(id)) != null){
            JOptionPane.showMessageDialog(null, "Id already found!");
            return false;
        }
        if(!email.contains("@")){
            JOptionPane.showMessageDialog(null, "Incorrect email address!");
            return false;
        }
        Client client = new Client(Integer.parseInt(id), name, address, email);
        try {
            clientDAO.insert(client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the delete method of the clientDAO class and deletes the client.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new client and deletes it from the Client table.
     * @param id the id of the client
     * @return true if the delete was completed successfully, and false otherwise
     */
    public boolean delete(String id){
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "The id is empty!");
            return false;
        }
        if(Integer.parseInt(id) <= 0){
            JOptionPane.showMessageDialog(null, "The id is negative!");
            return false;
        }
        Client client = clientDAO.findById(Integer.parseInt(id));
        if(client == null){
            JOptionPane.showMessageDialog(null, "Client not found!");
            return false;
        }
        try {
            clientDAO.delete(client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method calls the update method of the clientDAO class and updates the information about the client.
     * Firstly, it checks the parameters to have a correct value, other than null.
     * Then it creates a new client and updates its fields in the Client table.
     * @param id the id of the client
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     * @return true if the update was completed successfully, and false otherwise
     */
    public boolean update(String id, String name, String address, String email){
        if(id.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty parameters!!");
            return false;
        }
        if(Integer.parseInt(id) <= 0){
            JOptionPane.showMessageDialog(null, "Id cannot be negative!");
            return false;
        }
        if(!email.contains("@")){
            JOptionPane.showMessageDialog(null, "Incorrect email address!");
            return false;
        }
        if(clientDAO.findById(Integer.parseInt(id)) == null){
            JOptionPane.showMessageDialog(null, "Client not found!");
            return false;
        }
        Client client = new Client(Integer.parseInt(id), name, address, email);
        try {
            clientDAO.update(client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The method finds all the rows of the database table Client.
     * @return a list of clients found in the database table
     */
    public List<Client> findALl() {
        List<Client> clients = clientDAO.findAll();
        return clients;
    }
}
