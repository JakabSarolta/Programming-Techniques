package ro.tuc.pt.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JFrame {
    private JPanel contentPane;
    private JPanel numbersPanel;
    private JTextField firstNumberTextField;
    private JTextField secondNumberTextField;
    private JLabel resultValueLabel;

    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void prepareGui(){
        this.setSize(700, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane = new JPanel(new GridLayout(3, 2));
        this.prepareNumbersPanel();
        this.prepareButtonsPanel();
        this.prepareResultPanel();
        this.setContentPane(this.contentPane);
    }

    private void prepareResultPanel() {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1,1));
        JLabel resultLabel = new JLabel("Result:", JLabel.CENTER);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 20));
        this.resultValueLabel = new JLabel("", JLabel.CENTER);
        this.resultValueLabel.setFont(new Font("Serif", Font.BOLD, 20));
        resultPanel.add(resultLabel);
        resultPanel.add(this.resultValueLabel);
        resultPanel.setBackground(Color.orange);
        this.contentPane.add(resultPanel);
    }

    private void prepareNumbersPanel() {
        this.numbersPanel = new JPanel();
        this.numbersPanel.setLayout(new GridLayout(1, 2));
        JLabel firstNumberLabel = new JLabel("First polynomial", JLabel.CENTER);
        firstNumberLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        this.numbersPanel.add(firstNumberLabel);
        this.firstNumberTextField = new JTextField();
        firstNumberTextField.setBorder(BorderFactory.createMatteBorder(36, 0, 36, 0, Color.ORANGE));
        this.firstNumberTextField.setFont(new Font("Serif", Font.BOLD, 16));
        this.firstNumberTextField.setBackground(Color.PINK);
        this.numbersPanel.add(this.firstNumberTextField);
        JLabel secondNumberLabel = new JLabel("Second polynomial", JLabel.CENTER);
        secondNumberLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        this.numbersPanel.add(secondNumberLabel);
        this.secondNumberTextField = new JTextField();
        secondNumberTextField.setBorder(BorderFactory.createMatteBorder(36, 0, 36, 0, Color.ORANGE));
        this.secondNumberTextField.setFont(new Font("Serif", Font.BOLD, 16));
        this.secondNumberTextField.setBackground(Color.PINK);
        this.numbersPanel.add(secondNumberTextField);
        this.numbersPanel.setBackground(Color.orange);
        this.contentPane.add(this.numbersPanel);
    }

    private void prepareButtonsPanel(){
        JPanel operationsPanel = new JPanel();
        this.numbersPanel.setLayout(new GridLayout(1, 6));
        JButton computeButton1 = new JButton("Add");
        computeButton1.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton1.setForeground(Color.white);
        computeButton1.setActionCommand("Add");
        computeButton1.setBackground(Color.MAGENTA);
        computeButton1.addActionListener(this.controller);
        operationsPanel.add(computeButton1);
        JButton computeButton2 = new JButton("Subtract");
        computeButton2.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton2.setForeground(Color.white);
        computeButton2.setActionCommand("Subtract");
        computeButton2.setBackground(Color.MAGENTA);
        operationsPanel.add(computeButton2);
        computeButton2.addActionListener(this.controller);
        JButton computeButton3 = new JButton("Multiply");
        computeButton3.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton3.setForeground(Color.white);
        computeButton3.setActionCommand("Multiply");
        computeButton3.setBackground(Color.MAGENTA);
        operationsPanel.add(computeButton3);
        computeButton3.addActionListener(this.controller);
        JButton computeButton4 = new JButton("Divide");
        computeButton4.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton4.setForeground(Color.white);
        computeButton4.setActionCommand("Divide");
        computeButton4.setBackground(Color.MAGENTA);
        operationsPanel.add(computeButton4);
        computeButton4.addActionListener(this.controller);
        JButton computeButton5 = new JButton("Derivation");
        computeButton5.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton5.setForeground(Color.white);
        computeButton5.setActionCommand("Derivation");
        computeButton5.setBackground(Color.MAGENTA);
        operationsPanel.add(computeButton5);
        computeButton5.addActionListener(this.controller);
        JButton computeButton6 = new JButton("Integrate");
        computeButton6.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 13));
        computeButton6.setForeground(Color.white);
        computeButton6.setActionCommand("Integrate");
        computeButton6.setBackground(Color.MAGENTA);
        operationsPanel.add(computeButton6);
        computeButton6.addActionListener(this.controller);
        operationsPanel.setBackground(Color.orange);
        this.contentPane.add(operationsPanel);
    }

    public JTextField getFirstNumberTextField() {
        return firstNumberTextField;
    }

    public JTextField getSecondNumberTextField() {
        return secondNumberTextField;
    }

    public JLabel getResultValueLabel() {
        return resultValueLabel;
    }
}
