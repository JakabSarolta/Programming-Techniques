package ro.tuc.pt.data_access;

import ro.tuc.pt.business_logic.*;
import ro.tuc.pt.business_logic.MenuItem;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static ro.tuc.pt.business_logic.UserService.findUserByID;

public class FileWriter {
    private BufferedWriter writer;

    public FileWriter() {
    }

    public void createBill(Order order){
        openWriter("Bill.txt");
        try {
            writer.write("              BILL");
            writer.write("\n\nClient: " + findUserByID(order.getClientID()).getUserName());
            writer.write("\n\nProducts: ");
            for(MenuItem m:order.getOrderedProducts().keySet()){
                writer.write("\n" + m.getTitle());
                writer.write(" - amount: " + order.getOrderedProducts().get(m));
                writer.write(" - unit price: " +  m.getPrice());
            }
            writer.write("\n\nTotal: " + String.format("%.2f", order.getPrice()));
            closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void openWriter(String fileName) {
        try {
            writer = new BufferedWriter(new java.io.FileWriter(fileName));
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

    public void createTimeIntervalReport(Integer start, Integer end, ArrayList<Order> orders){
        openWriter("TimeIntervalReport.txt");
        try {
            writer.write("              REPORT");
            writer.write("\n\nStart hour: " + start);
            writer.write("\n\nEnding hour: " + end);
            writer.write("\n\nOrders: ");
            for(int i = 0; i < orders.size(); i++) {
                writer.write("\n" + orders.get(i).getOrderID());
                writer.write(" - client name: " + findUserByID(orders.get(i).getClientID()).getUserName());
                writer.write(" - start time: " + orders.get(i).getStartDate());
                writer.write(" - price: " +  orders.get(i).getPrice());
            }
            writer.write("\n\nTotal number of orders: " + orders.size());
            closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createNumberOfTimesReport(Integer limit, ArrayList<MenuItem> items){
        openWriter("NumberOfTimesReport.txt");
        try {
            writer.write("              REPORT");
            writer.write("\n\nOrder limit: " + limit);
            writer.write("\n\nMenu items: ");
            for(int i = 0; i < items.size(); i++) {
                writer.write("\n" + items.get(i).getTitle());
                writer.write(" - calories: " + items.get(i).getCaloriesS());
                writer.write(" - price: " +  items.get(i).getPrice());
            }
            writer.write("\n\nTotal number of menu items ordered more than " + limit + " number of times: " + items.size());
            closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createClientReport(Integer limit, Integer value, ArrayList<User> items){
        openWriter("ClientReport.txt");
        try {
            writer.write("              REPORT");
            writer.write("\n\nOrder limit: " + limit);
            writer.write("\n\nAmount limit: " + value);
            writer.write("\n\nMenu items: ");
            for(int i = 0; i < items.size(); i++) {
                writer.write("\nUserID: " + items.get(i).getUserID());
                writer.write(" - username: " + items.get(i).getUserName());
            }
            writer.write("\n\nTotal number clients: " + items.size());
            closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createMenuItemReport(Integer day, HashMap<MenuItem, Integer> items){
        openWriter("MenuItemReport.txt");
        try {
            writer.write("              REPORT");
            writer.write("\n\nDay of order: " + day);
            writer.write("\n\nMenu items: ");
            Set<MenuItem> menuItems = items.keySet();
            Object[] array = menuItems.toArray();
            for(int i = 0; i < items.size(); i++) {
                writer.write("\nMenu item: " + ((MenuItem)array[i]).getTitle());
                //writer.write(" - no of orders: " + items.get(i));
            }
            writer.write("\n\nTotal number clients: " + items.size());
            closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
