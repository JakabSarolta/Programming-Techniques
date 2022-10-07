package ro.tuc.pt.business_logic;

import ro.tuc.pt.data_access.Serializator;

import java.io.Serializable;
import java.rmi.server.UID;

public class User implements Serializable {
    private final UID userID;
    private String userName;
    private String password;
    private Status status;

    public User(String userName, String password, Status status) {
        userID = new UID();
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public UID getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
