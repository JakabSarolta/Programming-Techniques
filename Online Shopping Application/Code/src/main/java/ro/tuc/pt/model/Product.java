package ro.tuc.pt.model;

/**
 * to document the methods use:
 * @param
 * @return
 * comment - dao and business logic classes
 * tools - generate java doc - whole project
 * mysql workbench - create dump file: management - data export(structure & data, structure only, data only)
 *
 * method - arg: list of objects - generate the table for the clients and the products
 *
 * Project 4
 * lambda expressions
 * in the course: serialize and deserialize an object of a class: condition - implement serializable
 * hashCode + equals methods
 * hashMap: key - Order,
 * problem: insert in a hashmap a pair of orders, when we modify later the value of the key, we wont be able to
 * retrieve the value of that key anymore => immutable Order class
 */

/**
 *
 * The Product class models a product from the product table of the database.
 * It has private fields id, name, price, stock.
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private int stock;

    public Product() {
    }

    public Product(int id, String name, float price, int current_stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = current_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
