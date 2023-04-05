package ro.tuc.pt;

import ro.tuc.pt.gui.View;

import javax.swing.*;

public class App 
{
    public static void main( String[] args )
    {
        JFrame frame = new View("POLYNOMIAL CALCULATOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.pack();
        frame.setVisible(true);
    }
}
