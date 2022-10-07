package ro.tuc.pt.business_logic;

public class BaseProduct extends MenuItem{

    public BaseProduct() {
    }

    public BaseProduct(String title, float rating, Integer calories, Integer proteins, Integer fats, Integer sodium, Double price) {
        this.setTitle(title.trim());
        this.setRating(rating);
        this.setCalories(calories);
        this.setProteins(proteins);
        this.setFats(fats);
        this.setSodium(sodium);
        this.setPrice(price);
    }

    @Override
    public double computePrice(){
        return this.getPrice();
    }
}
