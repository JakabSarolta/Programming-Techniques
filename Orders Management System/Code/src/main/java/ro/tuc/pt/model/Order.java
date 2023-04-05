package ro.tuc.pt.model;

/**
 *
 * The Order class models an order from the order table of the database.
 * It has private fields idorder, idclient, idproduct, amount.
 */
public class Order {
    private int id;
    private int idclient;
    private int idproduct;
    private int amount;

    public Order() {
    }

    public Order(int idorder, int idclient, int idproduct, int amount) {
        this.id = idorder;
        this.idclient = idclient;
        this.idproduct = idproduct;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int idorder) {
        this.id = idorder;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
