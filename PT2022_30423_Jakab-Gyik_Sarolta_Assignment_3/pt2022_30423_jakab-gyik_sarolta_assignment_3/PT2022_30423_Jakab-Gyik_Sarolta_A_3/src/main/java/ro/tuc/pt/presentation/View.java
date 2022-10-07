package ro.tuc.pt.presentation;

import javax.swing.*;
import java.awt.*;

/**
 *
 * The View class extends JFrame and creates the initial window of the application.
 * From this window the user can select one of the 3 windows: client, product or order table.
 */
public class View extends JFrame {
    private final JPanel contentPane = new JPanel();
    private final JButton button1 = new JButton("Client");
    private final JButton button2 = new JButton("Product");
    private final JButton button3 = new JButton("Order");

    Controller controller = new Controller();
    Color backgroundColor = new Color(204, 255, 255);
    Color textBoxColor = new Color(102, 255, 255);

    public View(String title) throws HeadlessException {
        super(title);
        this.prepareGui();
    }

    public void prepareGui(){
        this.setSize(500, 500);
        contentPane.setLayout(new GridLayout(3, 3, 50, 50));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        button1.setActionCommand("Client");
        button1.addActionListener(controller);
        button1.setBackground(textBoxColor);
        button2.setBackground(textBoxColor);
        button3.setBackground(textBoxColor);
        contentPane.add(button1);
        button2.setActionCommand("Product");
        button2.addActionListener(controller);
        contentPane.add(button2);
        button3.setActionCommand("Order");
        button3.addActionListener(controller);
        contentPane.add(button3);
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        this.contentPane.setBackground(backgroundColor);
        this.setContentPane(this.contentPane);
        this.setVisible(true);
    }
}
