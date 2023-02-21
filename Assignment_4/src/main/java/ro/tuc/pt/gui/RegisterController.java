package ro.tuc.pt.gui;

import ro.tuc.pt.business_logic.Status;
import ro.tuc.pt.business_logic.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    UserService userService = new UserService();
    public final RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("register")){
            Status s = null;
            if(registerView.getStates() == 0){
                s = Status.ADMIN;
            } else if (registerView.getStates() == 1){
                s = Status.CLIENT;
            } else{
                s = Status.EMPLOYEE;
            }
            if(!userService.createUser(registerView.getUserName(), registerView.getPassWord(), s)){
                JOptionPane.showMessageDialog(null, "User already exists!");
            } else{
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            }
            registerView.dispose();
        }
    }
}
