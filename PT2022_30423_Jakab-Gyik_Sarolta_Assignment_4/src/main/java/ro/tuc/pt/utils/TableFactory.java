package ro.tuc.pt.utils;

import ro.tuc.pt.business_logic.MenuItem;
import ro.tuc.pt.business_logic.Order;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableFactory {
    public static DefaultTableModel createTable(List<String> t){
        String[] columns = {"Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price"};
        if(t.size() == 0){
            String[][] rows = new String[1][columns.length];
            return new DefaultTableModel(rows, columns);
        }
        String[][] rows = new String[t.size() / 7][7];

        int x = 0;
        for(int i = 0; i < t.size() / 7; i++) {
            for (int j = 0; j <= 6; j++) {
                rows[i][j] = t.get(x);
                x++;
            }
        }
        return new DefaultTableModel(rows, columns);
    }
    public static DefaultTableModel createTableFromHashMap(ArrayList<Order> t){
        String[] columns = {"Order ID", "Client ID", "Order date", "Product", "Quantity"};
        if(t.size() == 0){
            String[][] rows = new String[1][columns.length];
            return new DefaultTableModel(rows, columns);
        }
        int nrOfRows = 1;
        for(int i = 0; i < t.size(); i++) {
            nrOfRows += t.get(i).getOrderedProducts().keySet().size();
        }
        String[][] rows = new String[nrOfRows][5];
        for(int i = 0; i < 5; i++){
            rows[0][i] = columns[i];
        }
        int x = 1;
        for(int i = 0; i < t.size(); i++) {
            for(int j = x; j < x + t.get(i).getOrderedProducts().keySet().size(); j++){
                rows[j][0] = String.valueOf(t.get(i).getOrderID());
                rows[j][1] = String.valueOf(t.get(i).getClientID());
                rows[j][2] = String.valueOf(t.get(i).getStartDate());
            }
            for(MenuItem m :t.get(i).getOrderedProducts().keySet()){
                rows[x][3] = m.getTitle();
                x++;
            }
            x -= t.get(i).getOrderedProducts().keySet().size();
            for(Integer var: t.get(i).getOrderedProducts().values()){
                rows[x][4] = String.valueOf(var);
                x++;
            }
        }
        return new DefaultTableModel(rows, columns);
    }
}
