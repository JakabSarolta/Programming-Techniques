package ro.tuc.pt.gui;

import ro.tuc.pt.logic.SelectionPolicy;
import ro.tuc.pt.logic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener {
    private SimulationFrame frame;
    String strategies = "";
    public Controller(SimulationFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try{
            if(command.equals("START")) {


                int nrClients = Integer.parseInt(frame.getNumberOfClientsTextField().getText());
                if (nrClients <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of clients parameter");
                }
                int nrQueues = Integer.parseInt(frame.getNumberOfQueuesTextField().getText());
                if (nrQueues <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of queues parameter");
                }
                int simulationTime = Integer.parseInt(frame.getSimulationIntervalTextField().getText());
                if (simulationTime <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect simulation time parameter");
                }
                int tasksPerServer = Integer.parseInt(frame.getTasksPerServerTextField().getText());
                if (tasksPerServer <= 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of tasks per server");
                }
                int minArrivalTime = Integer.parseInt(frame.getMinArrivalTimeTextField().getText());
                if (minArrivalTime < 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect min. arrival time parameter");
                }
                int maxArrivalTime = Integer.parseInt(frame.getMaxArrivalTimeTextField().getText());
                if (maxArrivalTime < 0 || maxArrivalTime >= simulationTime || maxArrivalTime < minArrivalTime) {
                    JOptionPane.showMessageDialog(null, "Incorrect max. arrival time parameter");
                }
                int minServiceTime = Integer.parseInt(frame.getMinServiceTimeTextField().getText());
                if (minServiceTime < 0 || minServiceTime > simulationTime) {
                    JOptionPane.showMessageDialog(null, "Incorrect min. service time parameter");
                }
                int maxServiceTime = Integer.parseInt(frame.getMaxServiceTimeTextField().getText());
                if (maxServiceTime < 0 || maxServiceTime < minServiceTime) {
                    JOptionPane.showMessageDialog(null, "Incorrect max. service time parameter");
                }
                strategies = String.valueOf(frame.getStrategyComboBox().getSelectedItem());
                if (strategies == null) {
                    JOptionPane.showMessageDialog(null, "Missing strategy");
                }
                /*if (!strategies.equals("Time strategy") && tasksPerServer == 0){
                    JOptionPane.showMessageDialog(null, "Missing number of tasks");
                }*/

                SwingWorker<Void, ArrayList<String>> swingWorker = new SwingWorker<Void, ArrayList<String>>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        SimulationManager simulation1;
                        if(strategies.equals("Shortest queue strategy")) {
                            simulation1 = new SimulationManager(SelectionPolicy.SHORTEST_QUEUE, simulationTime, maxServiceTime, minServiceTime, maxArrivalTime, minArrivalTime, nrQueues, nrClients, tasksPerServer);
                        }else{
                            simulation1 = new SimulationManager(SelectionPolicy.SHORTEST_TIME, simulationTime, maxServiceTime, minServiceTime, maxArrivalTime, minArrivalTime, nrQueues, nrClients, tasksPerServer);
                        }

                        Thread myThread = new Thread(simulation1);
                        myThread.start();

                        while(simulation1.isRunning){
                            publish(simulation1.queues);
                            Thread.sleep(500);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<ArrayList<String>> chunks) {
                        ArrayList<String> result = chunks.get(chunks.size()-1);
                        frame.runSimulationWindow(nrQueues, result);
                        super.process(chunks);
                    }
                };

                swingWorker.execute();
        } else{
                if(command.equals("STOP")){
                    frame.removePanels();
                }
            }

        } catch(NumberFormatException n){
            JOptionPane.showMessageDialog(null, "Missing parameter(s)");
        }

    }
}
