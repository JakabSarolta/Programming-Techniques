package ro.tuc.pt.business_logic;

//Design by Contract™ is an approach to designing robust yet simple software.
// It provides methodological guidelines to achieve these goals without resorting to defensive programming.
// Instead, Design by Contract builds class invariants and pre/post validation of methods arguments
// and return values into the code itself.

import ro.tuc.pt.data_access.FileWriter;
import ro.tuc.pt.gui.LoginController;
import ro.tuc.pt.gui.employee.EmployeeController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.UID;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toList;

public class DeliveryService extends Observable implements DeliveryServiceProcessing{
    public static EmployeeController employeeController;
    FileWriter fileWriter = new FileWriter();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<Order> finishedOrders = new ArrayList<>();
    public static ArrayList<MenuItem> menuItems = new ArrayList();


    /**
     * To set the employeeController for the Observer pattern
     * @param employeeController the instance of the class that will connect the observer with the observable
     */
    public void addEmployeeObserver(EmployeeController employeeController){
        this.employeeController = employeeController;
    }

    /**
     * Checks if the menuItem is found already in the menu
     * @param b the baseProduct that we would like to add next
     * @return true if it is already present, false otherwise
     */
    public boolean checkDuplicate(BaseProduct b){
        for(MenuItem m:menuItems){
            if(m.getTitle().equals(b.getTitle())){
                return true;
            }
        }
        return false;
    }

    /**
     * The base products get imported from the file provided with the assignment.
     * @param fileName the name of the file where we import from
     */
    @Override
    public void importProducts(String fileName) {
        List<String> info = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> list = stream.collect(toList());

            for(String s: list){
                String[] str = s.split("\\,");
                for(String ss: str){
                    info.add(ss);
                    System.out.println(ss);
                }
                if(!s.equals(list.get(0))){
                    BaseProduct baseProduct = new BaseProduct(str[0], Float.parseFloat(str[1]), Integer.parseInt(str[2]),
                            Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]),
                            Double.parseDouble(str[6]));
                    if(!checkDuplicate(baseProduct)){
                        menuItems.add(baseProduct);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method generates an array of orders for the report for orders placed between a given time interval
     * @param start the start of the time interval in hours
     * @param end the end of the time interval in hours
     * @return the list of orders that have been placed
     */
    @Override
    public ArrayList<Order> generateTimeIntervalReport(Integer start, Integer end) {
        return orders.stream()
                .filter(order -> order.getStartDate().getHour() >= start && order.getStartDate().getHour() <= end)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * The method generates an array of menu items for the report for products ordered more than a minimum number of times
     * @param limit the minimum number of times a product must have been ordered
     * @return the list of menu items that have been ordered
     */
    @Override
    public ArrayList<MenuItem> generateNumberOfTimesReport(Integer limit) {
        Integer[] no = new Integer[menuItems.size()];
        fill(no, 0); //initialize all the elements with 0
        for(Order o: orders){
            for(MenuItem m: o.getOrderedProducts().keySet()){
                no[searchItemNo(m)]++;
            }
        }
        return menuItems.stream()
                .filter(menuItem -> no[menuItems.indexOf(menuItem)] > limit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Integer searchItemNo(MenuItem menuItem){
        int i = 0;
        for(MenuItem m: menuItems){
            if(m.getTitle().equals(menuItem.getTitle())){
                return i;
            }
            i++;
        }
        return null;
    }

    /**
     * The method generates an array of users for the report
     * @param orderLimit the minimum number of orders from the user
     * @param priceLimit the minimum value of the orders in total
     * @return an array list of users that correspond to the criteria
     */
    @Override
    public ArrayList<User> generateClientReport(Integer orderLimit, Double priceLimit) {
        Integer[] usersOrders = new Integer[UserService.users.size()];
        Double[] prices = new Double[UserService.users.size()];
        fill(usersOrders, 0); //initialize all the elements with 0
        fill(prices, Double.valueOf(0)); //initialize all the elements with 0
        for(Order o: orders){
            int no = findUserNo(o.getClientID());
            usersOrders[no]++;
            prices[no] += o.getPrice();
        }
        return UserService.users.stream()
                .filter(user -> usersOrders[UserService.users.indexOf(user)] > orderLimit && prices[UserService.users.indexOf(user)] > priceLimit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Integer findUserNo(UID id){
        int i = 0;
        for(User u:UserService.users){
            if(u.getUserID().equals(id))
                return i;
            i++;
        }
        return null;
    }

    /**
     * The method generates an array of menu items for the report
     * @param day the specific day on which the orders have been placed
     * @return a hash map of the items associated with the number of times they have been ordered
     */
    @Override
    public HashMap<MenuItem, Integer> generateMenuItemReport(Integer day) {
       HashMap<MenuItem, Integer> result = new HashMap<>();
       for(Order o: orders){
           if(o.getStartDate().getDayOfYear() == day){
               for(MenuItem m: o.getOrderedProducts().keySet()){
                   if(result.containsKey(m)){
                       result.replace(m, result.get(m), result.get(m) + 1);
                   } else{
                       result.put(m, 1);
                   }
               }
           }
       }
       /*Map<Set<MenuItem>, Integer> result = new HashMap<>();
       orders.get(0).getOrderedProducts().entrySet()
                .stream()
                .filter(entry -> orders.get(0).getStartDate().getDayOfYear() == day)
                .forEach(entry -> result.put(entry, entry.getValue()));
                */
       return result;
    }

    /**
     * The method transforms all the fields of the menu items in the array to a string list
     * @return the list of strings generated
     */
    public static List<String> menuItemsToString(){
        List<String> items = new ArrayList<>();
        for(MenuItem m:menuItems){
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

    /**
     * The method transforms the titles of the menu items in the menuItems array
     * @return the array of strings with the titles
     */
    public static String[] menuItemsToStrings(){
        String[] items = new String[menuItems.size()];
        int i = 0;
        for(MenuItem m:menuItems){
            items[i] = m.getTitle();
            i++;
        }
        return  items;
    }

    /**
     * The method adds a product to the list of menu items
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fats
     * @param sodium
     * @param price
     * @return true if the product was added, false otherwise
     */
    @Override
    public boolean addProduct(String title, Float rating, Integer calories, Integer proteins, Integer fats, Integer sodium, Double price) {
        assert title != null && rating != null && calories != null && proteins != null && fats != null && sodium != null && price != null;

        MenuItem item = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
        for(MenuItem m:menuItems){
            if(m.equals(item)){
                return false;
            }
        }
        menuItems.add(item);
        return true;
    }

    /**
     * The method deletes a product from the menu
     * @param title
     * @return true if the product was deleted, false otherwise
     */
    @Override
    public boolean deleteProduct(String title) {
        assert title != null;

        for(MenuItem m:menuItems){
            if(m.getTitle().equals(title)){
                menuItems.remove(m);
                return true;
            }
        }
        return false;
    }

    /**
     * The method modifies a product in the menu
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fats
     * @param sodium
     * @param price
     * @return true if the product was modified, false otherwise
     */
    @Override
    public boolean modifyProduct(String title, Float rating, Integer calories, Integer proteins, Integer fats, Integer sodium, Double price) {
        assert title != null && rating != null && calories != null && proteins != null && fats != null && sodium != null && price != null;

        title = title.trim();
        for(MenuItem m:menuItems){
            m.setTitle(m.getTitle().trim());
            if(m.getTitle().equals(title)){
                m.setRating(rating);
                m.setCalories(calories);
                m.setProteins(proteins);
                m.setFats(fats);
                m.setSodium(sodium);
                m.setPrice(price);
                return true;
            }
        }
        return false;
    }

    /**
     * The method created a composite product from base products
     * @param title title of the new menu item
     * @param products the base products in the nem composite product
     * @return true if the product was created, false otherwise
     */
    @Override
    public boolean createComposedProduct(String title, ArrayList<BaseProduct> products) {
        assert title != null && !products.isEmpty();

        CompositeProduct compositeProduct = new CompositeProduct(title, products);
        for(MenuItem m:menuItems){
            if(m.equals(compositeProduct)){
                return false;
            }
        }
        menuItems.add(compositeProduct);
        return true;
    }

    /**
     * The new order is created with the provided user information
     * @param products
     * @param quantities
     */
    @Override
    public void createOrder(ArrayList<MenuItem> products, ArrayList<Integer> quantities) {
        assert !products.isEmpty() && !quantities.isEmpty();

        Order newOrder = new Order(LoginController.currentUser.getUserID(), products, quantities);
        orders.add(newOrder);
        fileWriter.createBill(newOrder);
        if(employeeController != null){
            this.addObserver(employeeController);
            setChanged();
            notifyObservers(orders);
        }

        assert isWellFormed();
    }


    /**
     * Searches for menuItems that have their title contain the parameter
     * @param word the word which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByKeyword(String word) {
        assert word != null && !word.isEmpty();
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getTitle().contains(word))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their rating equal to the parameter
     * @param rating the rating which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByRating(Float rating) {
        assert rating != null && rating > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getRating().equals(rating))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their calories equal to the parameter
     * @param calories the calories which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByNumberOfCalories(Integer calories) {
        assert calories != null && calories > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getCalories().equals(calories))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their proteins equal to the parameter
     * @param proteins the proteins which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByProteins(Integer proteins) {
        assert proteins != null && proteins > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getProteins().equals(proteins))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their fats equal to the parameter
     * @param fats the fats which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByFats(Integer fats) {
        assert fats != null && fats > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getFats().equals(fats))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their sodium equal to the parameter
     * @param sodium the sodium which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductBySodium(Integer sodium) {
        assert sodium != null && sodium > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getSodium().equals(sodium))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Searches for menuItems that have their price equal to the parameter
     * @param price the price which the method searches for
     * @return an array list of menuItems that correspond to the search criteria
     */
    @Override
    public ArrayList<MenuItem> searchProductByPrice(Double price) {
        assert price != null && price > 0;
        assert isWellFormed();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getPrice().equals(price))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isWellFormed(){
        if(orders != null && menuItems != null && finishedOrders != null)
            return true;
        return false;
    }
}
