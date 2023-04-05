package ro.tuc.pt.business_logic;

import java.util.ArrayList;
import java.util.HashMap;

public interface DeliveryServiceProcessing {
    //for the admin
    void importProducts(String fileName);
    ArrayList<Order> generateTimeIntervalReport(Integer start, Integer end);
    ArrayList<MenuItem> generateNumberOfTimesReport(Integer limit);
    ArrayList<User> generateClientReport(Integer orderLimit, Double priceLimit);
    HashMap<MenuItem, Integer> generateMenuItemReport(Integer day);
    boolean addProduct(String title, Float rating, Integer calories, Integer proteins, Integer fats, Integer sodium, Double price);
    boolean deleteProduct(String title);
    boolean modifyProduct(String title, Float rating, Integer calories, Integer proteins, Integer fats, Integer sodium, Double price);
    boolean createComposedProduct(String title, ArrayList<BaseProduct> products);

    //for the client

    /**
     * @pre !products.isEmpty() && !quantities.isEmpty()
     * @param products
     * @param quantities
     */
    void createOrder(ArrayList<MenuItem> products, ArrayList<Integer> quantities);

    /**
     * @pre word != null
     * @post forall i: [0 ... getSize()-1]
     *        result.get(i).getTitle().contains(text)
     * @param word
     * @return
     */
    ArrayList<MenuItem> searchProductByKeyword(String word);

    /**
     * @pre rating != null
     * @post forall i: [0 ... getSize()-1]
     *          result.get(i).getRating().equals(rating)
     * @param rating
     * @return
     */
    ArrayList<MenuItem> searchProductByRating(Float rating);

    /**
     * @pre calories != null
     * @post forall i: [0 ... getSize()-1]
     *      result.get(i).getCalories().equals(calories)
     * @param calories
     * @return
     */
    ArrayList<MenuItem> searchProductByNumberOfCalories(Integer calories);

    /**
     * @pre proteins != null
     * @post forall i: [0 ... getSize()-1]
     *      result.get(i).getProteins().equals(proteins)
     * @param proteins
     * @return
     */
    ArrayList<MenuItem> searchProductByProteins(Integer proteins);

    /**
     * @pre fats != null
     * @post forall i: [0 ... getSize()-1]
     *      result.get(i).getFats().equals(fats)
     * @param fats
     * @return
     */
    ArrayList<MenuItem> searchProductByFats(Integer fats);

    /**
     * @pre sodium != null
     * @post forall i: [0 ... getSize()-1]
     *      result.get(i).getSodium().equals(sodium)
     * @param sodium
     * @return
     */
    ArrayList<MenuItem> searchProductBySodium(Integer sodium);

    /**
     * @pre price != null
     * @post forall i: [0 ... getSize()-1]
     *      result.get(i).getPrice().equals(price)
     * @param price
     * @return
     */
    ArrayList<MenuItem> searchProductByPrice(Double price);
}
