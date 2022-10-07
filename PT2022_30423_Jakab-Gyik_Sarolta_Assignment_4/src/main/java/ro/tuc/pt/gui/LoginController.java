package ro.tuc.pt.gui;

import ro.tuc.pt.business_logic.Status;
import ro.tuc.pt.business_logic.User;
import ro.tuc.pt.business_logic.UserService;
import ro.tuc.pt.gui.admin.AdministratorView;
import ro.tuc.pt.gui.client.ClientView;
import ro.tuc.pt.gui.employee.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    UserService userService = new UserService();
    public final LoginView loginView;
    public static User currentUser;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("enter")){
            Status s = null;
            if(loginView.getStates() == 0){
                s = Status.ADMIN;
            } else if (loginView.getStates() == 1){
                s = Status.CLIENT;
            } else{
                s = Status.EMPLOYEE;
            }
            if(!userService.checkUsers(loginView.getUserName(), loginView.getPassWord(), s)){
                JOptionPane.showMessageDialog(null, "User does not exist! Please register!");
            } else{
                currentUser = userService.findUser(loginView.getUserName(), loginView.getPassWord(), s);
                if(loginView.getStates() == 0){
                    AdministratorView administratorView = new AdministratorView("ADMIN WINDOW");
                } else if (loginView.getStates() == 1){
                    ClientView clientView = new ClientView("CLIENT WINDOW");
                } else{
                    EmployeeView employeeView = new EmployeeView("EMPLOYEE WINDOW");
                }
            }
        }
        else if (command.equals("register")){
            RegisterView registerView = new RegisterView("REGISTRY");
        }
    }
}
