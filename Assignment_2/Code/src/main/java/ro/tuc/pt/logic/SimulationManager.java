package ro.tuc.pt.logic;

import ro.tuc.pt.gui.SimulationFrame;
import ro.tuc.pt.model.Server;
import ro.tuc.pt.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable{
    private Scheduler scheduler;
    private SimulationFrame frame; //reference to the gui
    private List<Task> tasks;
    private SelectionPolicy selectionPolicy; //combo box
    public boolean isRunning = true;
    public ArrayList<String> queues = new ArrayList<>();
    public Integer peakSecond = 0;


    //read from ui:
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public int tasksPerServer;

    public SimulationManager(SelectionPolicy selectionPolicy, int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients, int tasksPerServer) {
        this.selectionPolicy = selectionPolicy;
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.tasksPerServer = tasksPerServer;
        this.scheduler = new Scheduler(numberOfServers, tasksPerServer); //create server obj and corresponding thread
        this.frame = new SimulationFrame("Queue Management Application");
        tasks = new ArrayList<>();
        generateRandomTasks();
        checkPeakSecond();
    }

    public void checkPeakSecond(){
        ArrayList<Integer> seconds = new ArrayList<>();
        for (int i = 0; i < timeLimit; i++){
            seconds.add(0);
        }
        for (int i = 0; i < tasks.size(); i++){
            seconds.set(tasks.get(i).getArrivalTime(), seconds.get(tasks.get(i).getArrivalTime()) + 1);
        }
        for (int i = 0; i < timeLimit; i++){
            if (seconds.get(i) > peakSecond){
                peakSecond = i;
            }
        }
    }

    public void generateRandomTasks(){
        System.out.println("Generated random tasks:");
        for (int i = 0; i < numberOfClients; i++){
            int arrivalTime = (int) Math.floor(Math.random()*(maxArrivalTime-minArrivalTime+1)+minArrivalTime);
            int processingTime = (int) Math.floor(Math.random()*(maxProcessingTime-minProcessingTime+1)+minProcessingTime);
            Task newTask = new Task(arrivalTime, processingTime);
            System.out.println("(" + newTask.getId() + ", " + newTask.getArrivalTime() + ", " + newTask.getServiceTime() + ")");
            tasks.add(newTask);
        }
    }

    public String waitingTasks(){
        String waiting = "";
        for (int i = 0; i < tasks.size(); i++){
            waiting += "(" + tasks.get(i).getId() + ", " + tasks.get(i).getArrivalTime() + ", " + tasks.get(i).getServiceTime() + ")  ";
        }
        return waiting;
    }

    public ArrayList<String> queuedTasks(ArrayList<Server> servers) throws InterruptedException {
        ArrayList<String> queuedTasks = new ArrayList<>();
        for (int i = 0; i < servers.size(); i++){
            if (servers.get(i).getTasks().size() == 0){
                queuedTasks.add("closed");
            } else{
                queuedTasks.add(servers.get(i).getStringTasks());
            }
        }
        return queuedTasks;
    }

    @Override
    public void run() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("log.txt");

            isRunning = true;
            int currentTime = 0;
            while(currentTime < timeLimit){
                for (int i = 0; i < scheduler.getServers().size(); i++){
                    if (scheduler.getServers().get(i).getTasks().size() != 0){
                        scheduler.getServers().get(i).getTasks().element().setServiceTime(scheduler.getServers().get(i).getTasks().element().getServiceTime() - 1);
                        scheduler.getServers().get(i).setWaitingPeriod(new AtomicInteger(scheduler.getServers().get(i).getWaitingPeriod().intValue() - 1));
                        if (scheduler.getServers().get(i).getTasks().element().getServiceTime() == 0){
                            scheduler.getServers().get(i).getTasks().remove();
                        }
                    }
                }
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getArrivalTime() <= currentTime){
                        if(scheduler.dispatchTask(tasks.get(i), selectionPolicy)){
                            tasks.remove(i);
                            i--;
                        }
                    }
                }

                try {
                    queues = queuedTasks(scheduler.getServers());
                    queues.add(0, "" + currentTime);
                    queues.add(1, waitingTasks());
                    myWriter.write("Time " + queues.get(0) + '\n');
                    myWriter.write("Waiting clients " + queues.get(1) + '\n');
                    for (int i = 2; i < queues.size(); i++){
                        myWriter.write("Queue " + (i-1) + ": " + queues.get(i) + '\n');
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentTime++;
                try {
                    Server.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (selectionPolicy == SelectionPolicy.SHORTEST_TIME){
                myWriter.write("The avg waiting time is: " + TimeStrategy.waitingTime / numberOfClients + '\n');
                myWriter.write("The avg service time is: " + TimeStrategy.serviceTime / numberOfClients + '\n');
            } else{
                myWriter.write("The avg waiting time is: " + ShortestQueueStrategy.waitingTime / numberOfClients + '\n');
                myWriter.write("The avg service time is: " + ShortestQueueStrategy.serviceTime / numberOfClients + '\n');

            }
            myWriter.write("The peak second is: " + peakSecond + '\n');

            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isRunning = false;
    }
}
