package ro.tuc.pt.business_logic;

import ro.tuc.pt.data_access.Serializator;

import java.rmi.server.UID;
import java.util.ArrayList;

public class UserService {
    Serializator<User> s = new Serializator<>();
    public static ArrayList<User> users = new ArrayList<User>();

    public boolean checkUsers(String userName, String password, Status status){
        for(User u: users){
            if(u.getUserName().equals(userName) && u.getPassword().equals(password) && u.getStatus().equals(status)){
                return true;
            }
        }
        return false;
    }

    public boolean createUser(String userName, String password, Status status){
        if(!checkUsers(userName, password, status)){
            User newUser = new User(userName, password, status);
            users.add(newUser);
            return true;
        }
        return false;
    }
    public User findUser(String userName, String password, Status status){
        for(User u: users){
            if(u.getUserName().equals(userName) && u.getPassword().equals(password) && u.getStatus().equals(status)){
                return u;
            }
        }
        return null;
    }
    public static User findUserByID(UID id){
        for(User u: users){
            if(u.getUserID().equals(id)){
                return u;
            }
        }
        return null;
    }
}
