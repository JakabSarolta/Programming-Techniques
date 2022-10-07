package ro.tuc.pt;

import ro.tuc.pt.gui.SimulationFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new SimulationFrame("Queue Management Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }
}
