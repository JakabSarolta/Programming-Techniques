package ro.tuc.pt.gui.client;

import ro.tuc.pt.business_logic.DeliveryService;
import ro.tuc.pt.business_logic.MenuItem;
import ro.tuc.pt.utils.TableFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientController implements ActionListener {
    DeliveryService deliveryService = new DeliveryService();
    public final ClientView clientView;
    ArrayList<MenuItem> products = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();

    public ClientController(ClientView clientView) {
        //deliveryService.importProducts("products.csv");
        this.clientView = clientView;
        updateTable(DeliveryService.menuItemsToString());
    }

    public ArrayList<MenuItem> intersection(ArrayList<MenuItem> a, ArrayList<MenuItem> b){
        if(a.isEmpty()){
            return b;
        }
        if(b.isEmpty()){
            return a;
        }
        ArrayList<MenuItem> c = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
            for(int j = 0; j < b.size(); j++){
                if(a.get(i).getTitle().equals(b.get(j).getTitle())){
                    c.add(a.get(i));
                }
            }
        }
        return c;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "search":
                List<MenuItem> items = DeliveryService.menuItems.stream()
                        .filter(menuItem -> menuItem.getTitle().contains(clientView.getKeywordT())
                                && menuItem.getRatingS().contains(clientView.getRatingT())
                                && menuItem.getCaloriesS().contains(clientView.getCaloriesT())
                                && menuItem.getProteinsS().contains(clientView.getProteinsT())
                                && menuItem.getFatsS().contains(clientView.getFatsT())
                                && menuItem.getSodiumS().contains(clientView.getSodiumT())
                                && menuItem.getPriceS().contains(clientView.getPriceT()))
                        .toList();
                ArrayList<MenuItem> itm = new ArrayList<>();
                itm.addAll(items);
                updateTable(menuItemsToString(itm));
                break;
            case "add":
                products.add(DeliveryService.menuItems.get(clientView.getProducts()));
                quantity.add(clientView.getQuantityS());
                if(clientView.getOrder().equals("")){
                    clientView.setOrder(DeliveryService.menuItems.get(clientView.getProducts()).getTitle() + " " + clientView.getQuantityS());
                } else{
                    clientView.setOrder(clientView.getOrder() + ", " + DeliveryService.menuItems.get(clientView.getProducts()).getTitle() + " " + clientView.getQuantityS());
                }
                 break;
            case "create":
                if(!products.isEmpty()){
                    deliveryService.createOrder(products, quantity);
                    clientView.setOrder("");
                    JOptionPane.showMessageDialog(null, "The order has been created!!");
                } else{
                    JOptionPane.showMessageDialog(null, "Cannot create an empty order! Please add some products first");
                }
                break;
            default:

        }
        //updateTable(DeliveryService.menuItemsToString()); //updating the table for gui
    }
    public void updateTable(List<String> list){
        DefaultTableModel model = TableFactory.createTable(list);
        clientView.table.setModel(model);
    }
    public static List<String> menuItemsToString(ArrayList<MenuItem> selectedItems){
        List<String> items = new ArrayList<>();
        for(MenuItem m:selectedItems){
            items.add(m.getTitle());
            items.add(String.valueOf(m.getRating()));
            items.add(String.valueOf(m.getCalories()));
            items.add(String.valueOf(m.getProteins()));
            items.add(String.valueOf(m.getFats()));
            items.add(String.valueOf(m.getSodium()));
            items.add(String.valueOf(m.getPrice()));
        }
        return  items;
    }
}
