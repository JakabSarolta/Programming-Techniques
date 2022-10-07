package ro.tuc.pt;

import ro.tuc.pt.data_access.AbstractDAO;
import ro.tuc.pt.data_access.ClientDAO;
import ro.tuc.pt.data_access.ProductDAO;
import ro.tuc.pt.model.Client;
import ro.tuc.pt.model.Order;
import ro.tuc.pt.model.Product;
import ro.tuc.pt.presentation.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("App");
        /*Client client = new Client(3, "Zoli", "Valamilyen utca", "kjhsdbc@yahoo.com");
        //Order order = new Order();
        Product product = new Product(4, "kavescsesze", 15, 12);
        ClientDAO absDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();

        try {
            productDAO.insert(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        /*try {
            absDAO.delete(client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        /*for(Client c:absDAO.findAll()){
            System.out.println(c.getName() + " " + c.getId());
        }*/
    }
}
