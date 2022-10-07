package ro.tuc.pt.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {
    private JPanel contentPane = null;
    private JPanel numbersPanel;
    private JPanel timePanel;
    private JPanel buttonSpacer;
    private JTextField numberOfClientsTextField;
    private JTextField numberOfQueuesTextField;
    private JTextField simulationIntervalTextField;
    private JComboBox strategyComboBox;
    private JTextField minArrivalTimeTextField;
    private JTextField maxArrivalTimeTextField;
    private JTextField minServiceTimeTextField;
    private JTextField maxServiceTimeTextField;
    private JTextField tasksPerServerTextField;

    private JPanel resultsPane = null;
    private ArrayList<JLabel> tupleLabels = new ArrayList<>();
    private JLabel waiting = new JLabel("", JLabel.CENTER);
    private JLabel time = new JLabel("", JLabel.CENTER);

    Controller controller = new Controller(this);
    Color backgroundColor = new Color(204, 255, 255);
    Color textBoxColor = new Color(102, 255, 255);

    public SimulationFrame(String title) throws HeadlessException {
        super(title);
        this.prepareGui();
    }

    public void prepareGui(){
        if (contentPane != null){
           contentPane.remove(resultsPane);
           contentPane.remove(buttonSpacer);
        } else{
            contentPane = new JPanel();
        }
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane.setLayout(new GridLayout(2, 2));
        this.prepareNumbersPanel();
        this.prepareTimePanel();

        buttonSpacerSetup();
        JButton startSimulationButton = new JButton("START");
        startSimulationButton.setActionCommand("START");
        startSimulationButton.setBackground(Color.cyan);
        startSimulationButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 18));
        startSimulationButton.addActionListener(this.controller);
        buttonSpacer.add(startSimulationButton);
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        contentPane.add(buttonSpacer);

        this.contentPane.setBackground(backgroundColor);
        this.setContentPane(this.contentPane);
    }

    private void prepareNumbersPanel() {
        numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(5, 2));
        numbersPanel.setBackground(backgroundColor);
        JLabel numberOfClientsLabel = new JLabel("Number of clients:", JLabel.CENTER);
        numberOfClientsLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        numbersPanel.add(numberOfClientsLabel);
        this.numberOfClientsTextField = new JTextField();
        this.numberOfClientsTextField.setBackground(textBoxColor);
        numbersPanel.add(numberOfClientsTextField);
        JLabel numberOfQueuesLabel = new JLabel("Number of queues:", JLabel.CENTER);
        numberOfQueuesLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        numberOfQueuesLabel.setBackground(backgroundColor);
        numbersPanel.add(numberOfQueuesLabel);
        this.numberOfQueuesTextField = new JTextField();
        this.numberOfQueuesTextField.setBackground(textBoxColor);
        numbersPanel.add(numberOfQueuesTextField);
        JLabel simulationIntervalLabel = new JLabel("Simulation time:", JLabel.CENTER);
        simulationIntervalLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        simulationIntervalLabel.setBackground(backgroundColor);
        numbersPanel.add(simulationIntervalLabel);
        this.simulationIntervalTextField = new JTextField();
        this.simulationIntervalTextField.setBackground(textBoxColor);
        numbersPanel.add(simulationIntervalTextField);
        JLabel strategyLabel = new JLabel("Strategy applied:", JLabel.CENTER);
        strategyLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        strategyLabel.setBackground(backgroundColor);
        numbersPanel.add(strategyLabel);
        String[] strategies = new String[]{"Time strategy", "Shortest queue strategy"};
        this.strategyComboBox = new JComboBox(strategies);
        this.strategyComboBox.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        this.strategyComboBox.setBackground(textBoxColor);
        numbersPanel.add(strategyComboBox);
        JLabel tasksPerServerLabel = new JLabel("Max nr of tasks/server:", JLabel.CENTER);
        tasksPerServerLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        tasksPerServerLabel.setBackground(backgroundColor);
        numbersPanel.add(tasksPerServerLabel);
        this.tasksPerServerTextField = new JTextField();
        this.tasksPerServerTextField.setBackground(textBoxColor);
        numbersPanel.add(tasksPerServerTextField);
        this.contentPane.add(numbersPanel);
    }

    private void prepareTimePanel() {
        timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(4, 2));
        timePanel.setBackground(backgroundColor);
        JLabel minArrivalTimeLabel = new JLabel("Min. arrival time:", JLabel.CENTER);
        minArrivalTimeLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        minArrivalTimeLabel.setBackground(backgroundColor);
        timePanel.add(minArrivalTimeLabel);
        this.minArrivalTimeTextField = new JTextField();
        this.minArrivalTimeTextField.setBackground(textBoxColor);
        timePanel.add(minArrivalTimeTextField);
        JLabel maxArrivalTimeLabel = new JLabel("Max. arrival time:", JLabel.CENTER);
        maxArrivalTimeLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        maxArrivalTimeLabel.setBackground(backgroundColor);
        timePanel.add(maxArrivalTimeLabel);
        this.maxArrivalTimeTextField = new JTextField();
        this.maxArrivalTimeTextField.setBackground(textBoxColor);
        timePanel.add(maxArrivalTimeTextField);
        JLabel minServiceTimeLabel = new JLabel("Min. service time:", JLabel.CENTER);
        minServiceTimeLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        minServiceTimeLabel.setBackground(backgroundColor);
        timePanel.add(minServiceTimeLabel);
        this.minServiceTimeTextField = new JTextField();
        this.minServiceTimeTextField.setBackground(textBoxColor);
        timePanel.add(minServiceTimeTextField);
        JLabel maxServiceTimeLabel = new JLabel("Max. service time:", JLabel.CENTER);
        maxServiceTimeLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        maxServiceTimeLabel.setBackground(backgroundColor);
        timePanel.add(maxServiceTimeLabel);
        this.maxServiceTimeTextField = new JTextField();
        this.maxServiceTimeTextField.setBackground(textBoxColor);
        timePanel.add(maxServiceTimeTextField);

        this.contentPane.add(timePanel);
    }

    public void runSimulationWindow(int nrQueues, ArrayList<String> simulationInfo){
        if (simulationInfo.isEmpty()){
            return;
        }
        if (resultsPane == null || resultsPane.getComponent(0) == numbersPanel){
            contentPane.remove(numbersPanel);
            contentPane.remove(timePanel);
            contentPane.remove(buttonSpacer);
        } else{
            contentPane.remove(resultsPane);
            contentPane.remove(buttonSpacer);
        }


        contentPane.setLayout(new GridLayout(2, 1));
        resultsPane = new JPanel(new GridLayout(nrQueues + 2, 1));
        this.resultsPane.setBackground(backgroundColor);

        JLabel currTime = new JLabel("Time:", JLabel.CENTER);
        currTime.setBackground(backgroundColor);
        currTime.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
        resultsPane.add(currTime);

        time.setText(String.valueOf(simulationInfo.get(0)));
        time.setBackground(backgroundColor);
        time.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        resultsPane.add(time);

        JLabel waitingList = new JLabel("Waiting:", JLabel.CENTER);
        waitingList.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
        waitingList.setBackground(backgroundColor);
        resultsPane.add(waitingList);

        waiting.setText(simulationInfo.get(1));
        waiting.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 12));
        waiting.setBackground(backgroundColor);
        resultsPane.add(waiting);

        ArrayList<JLabel> queueLabels = new ArrayList<>();
        tupleLabels = new ArrayList<>();
        for (int i = 0; i < nrQueues; i++){
            queueLabels.add(new JLabel("Queue" + (i+1) + ":", JLabel.CENTER));
            queueLabels.get(i).setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
            queueLabels.get(i).setBackground(backgroundColor);
            tupleLabels.add(new JLabel(simulationInfo.get(i + 2), JLabel.CENTER));
            tupleLabels.get(i).setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
            tupleLabels.get(i).setBackground(backgroundColor);
            resultsPane.add(queueLabels.get(i));
            resultsPane.add(tupleLabels.get(i));
        }
        buttonSpacerSetup();
        JButton stopSimulationButton = new JButton("STOP");
        stopSimulationButton.setActionCommand("STOP");
        stopSimulationButton.setBackground(Color.cyan);
        stopSimulationButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 18));
        stopSimulationButton.addActionListener(this.controller);
        buttonSpacer.add(stopSimulationButton);
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));

        this.contentPane.add(resultsPane);
        this.contentPane.add(buttonSpacer);
        this.setContentPane(this.contentPane);
    }

    private void buttonSpacerSetup() {
        buttonSpacer = new JPanel(new GridLayout(3, 3));
        buttonSpacer.setBackground(backgroundColor);
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
        buttonSpacer.add(new JLabel(""));
    }

    public void removePanels(){
        contentPane.remove(resultsPane);
        contentPane.remove(buttonSpacer);
        prepareGui();
    }

    public void setWaiting(String waiting) {
        this.waiting.setText(waiting);
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public JTextField getTasksPerServerTextField() {
        return tasksPerServerTextField;
    }

    public JTextField getNumberOfClientsTextField() {
        return numberOfClientsTextField;
    }

    public JTextField getNumberOfQueuesTextField() {
        return numberOfQueuesTextField;
    }

    public JTextField getSimulationIntervalTextField() {
        return simulationIntervalTextField;
    }

    public JComboBox getStrategyComboBox() {
        return strategyComboBox;
    }

    public JTextField getMinArrivalTimeTextField() {
        return minArrivalTimeTextField;
    }

    public JTextField getMaxArrivalTimeTextField() {
        return maxArrivalTimeTextField;
    }

    public JTextField getMinServiceTimeTextField() {
        return minServiceTimeTextField;
    }

    public JTextField getMaxServiceTimeTextField() {
        return maxServiceTimeTextField;
    }
}
