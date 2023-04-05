package ro.tuc.pt.business_logic;

import java.io.Serializable;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Order implements Serializable {
    private final UID OrderID;
    private final UID ClientID;
    private final LocalDateTime startDate;
    private LocalDateTime endDate = null;
    private  HashMap<MenuItem, Integer>orderedProducts = new HashMap<>();
    private Double price = Double.valueOf(0);

    public Order(UID clientID, ArrayList<MenuItem> products, ArrayList<Integer> quantities) {
        OrderID = new UID();
        ClientID = clientID;
        startDate = LocalDateTime.now();
        for(int i = 0; i < products.size(); i++){
            orderedProducts.put(products.get(i), quantities.get(i));
        }
        int i = 0;
        for(MenuItem m:products){
            this.price += m.getPrice() * quantities.get(i);
            i++;
        }
    }

    public UID getOrderID() {
        return OrderID;
    }

    public UID getClientID() {
        return ClientID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public HashMap<MenuItem, Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(HashMap<MenuItem, Integer> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
    public void addOrderedProduct(MenuItem menuItem, Integer quantity) {
        orderedProducts.put(menuItem, quantity);
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return OrderID.equals(order.OrderID) && ClientID.equals(order.ClientID) && startDate.equals(order.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OrderID, ClientID, startDate);
    }
}
