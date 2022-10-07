package ro.tuc.pt.business_logic;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private ArrayList<BaseProduct> products = new ArrayList<>();

    public CompositeProduct(String title, ArrayList<BaseProduct> products) {
        this.setTitle(title.trim());
        this.setProducts(products);
        setPrice(computePrice());
        setCalories(computeCalories());
        setProteins(computeProteins());
        setFats(computeFats());
        setSodium(computeSodium());
        setRating(computeRating());
    }

    public ArrayList<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BaseProduct> products) {
        this.products = products;
    }

    public Float computeRating(){
        Float rating = (float) 0;
        for(BaseProduct b: products){
            rating += b.getRating();
        }
        return rating / products.size();
    }
    public Integer computeSodium(){
        Integer sodium = 0;
        for(BaseProduct b: products){
            sodium += b.getSodium();
        }
        return sodium;
    }
    public Integer computeFats(){
        Integer fats = 0;
        for(BaseProduct b: products){
            fats += b.getFats();
        }
        return fats;
    }
    public Integer computeProteins(){
        Integer proteins = 0;
        for(BaseProduct b: products){
            proteins += b.getProteins();
        }
        return proteins;
    }
    public Integer computeCalories(){
        Integer calories = 0;
        for(BaseProduct b: products){
            calories += b.getCalories();
        }
        return calories;
    }
    @Override
    public double computePrice(){
        double price = 0;
        for(BaseProduct b: products){
            price += b.getPrice();
        }
        return price;
    }
}
