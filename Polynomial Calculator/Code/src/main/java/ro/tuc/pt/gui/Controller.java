package ro.tuc.pt.gui;

import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private View view;

    private Operations operations = new Operations();

    public Controller(View v){
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "Add" || command == "Subtract" || command == "Multiply" || command == "Divide" || command == "Derivation" || command == "Integrate"){
            String firstPolynomial = view.getFirstNumberTextField().getText();
            String secondPolynomial = view.getSecondNumberTextField().getText();
            Polynomial firstP = operations.transformStringToPolynomial(firstPolynomial);
            Polynomial secondP = operations.transformStringToPolynomial(secondPolynomial);
            Polynomial result = new Polynomial();
            switch(command){
                case "Add": result = operations.add(firstP, secondP);
                    break;
                case "Subtract": result = operations.subtract(firstP, secondP);
                    break;
                case "Multiply": result = operations.multiply(firstP, secondP);
                    break;
                case "Divide": result = operations.divide(firstP, secondP);
                    break;
                case "Derivation": result = operations.derivation(firstP);
                    break;
                case "Integrate": result = operations.integrate(firstP);
                    break;
            }
            view.getResultValueLabel().setText(operations.transformPolynomialToString(result));
        }
    }
}
