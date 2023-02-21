package ro.tuc.pt.business_logic;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String title;
    private Float rating;
    private Integer calories;
    private Integer proteins;
    private Integer fats;
    private Integer sodium;
    private Double price = Double.valueOf(0);

    public MenuItem() {
    }


    public Float getRating() { return rating; }
    public String getRatingS() { return String.valueOf(rating); }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getCalories() {
        return calories;
    }
    public String getCaloriesS() {
        return String.valueOf(calories);
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }
    public String getProteinsS() {
        return String.valueOf(proteins);
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }
    public String getFatsS() {
        return String.valueOf(fats);
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getSodium() {
        return sodium;
    }
    public String getSodiumS() {
        return String.valueOf(sodium);
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }
    public String getPriceS() {
        return String.valueOf(price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double computePrice(){
        return price;
    }
}
