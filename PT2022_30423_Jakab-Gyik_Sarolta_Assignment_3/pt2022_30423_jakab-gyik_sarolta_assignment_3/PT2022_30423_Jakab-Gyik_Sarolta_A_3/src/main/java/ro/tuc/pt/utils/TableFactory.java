package ro.tuc.pt.utils;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * The TableFactory class realizes the connection to the mySQL Order_Management database and creates
 * a table model for each table to print on the screen.
 */
public class TableFactory {
    /**
     * The createTable method uses a table from the database and creates a DefaultTableModel for the GUI to use.
     * Invoked by the updateTable method from the Controller classes.
     * @param t the name of the result set given as parameter
     * @param <T> the generic list that uses the reflection technique and gets the result sets
     * @return the DefaultTableModel that was created and initialized
     */
    public static <T>DefaultTableModel createTable(List<T> t){
        if(t.size() == 0)
            return new DefaultTableModel();
        String[] columns = new String[t.get(0).getClass().getDeclaredFields().length];
        String[][] rows = new String[t.size() + 1][t.get(0).getClass().getDeclaredFields().length + 1];
        int i = 0;
        for(Field f:t.get(0).getClass().getDeclaredFields()){
            columns[i] = f.getName();
            rows[0][i] = f.getName();
            i++;
        }
        i = 1;
        int j = 0;
        for(T tt: t){
            for(Field ff:tt.getClass().getDeclaredFields()){
                try {
                    ff.setAccessible(true);
                    rows[i][j] = ff.get(tt) + "";
                    ff.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                j++;
            }
            j = 0;
            i++;
        }
        DefaultTableModel table = new DefaultTableModel(rows, columns);
        return table;
    }
}
