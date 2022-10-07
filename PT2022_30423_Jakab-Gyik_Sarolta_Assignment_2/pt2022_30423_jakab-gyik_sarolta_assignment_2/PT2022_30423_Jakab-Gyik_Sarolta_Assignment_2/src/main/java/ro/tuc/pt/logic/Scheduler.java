package ro.tuc.pt.logic;

import ro.tuc.pt.model.Server;
import ro.tuc.pt.model.Task;

import java.util.ArrayList;

public class Scheduler{
    private ArrayList<Server> servers = new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer; //if using the array list implementation of the blocking queue - take capacity from here
    private TimeStrategy strategy1 = new TimeStrategy();
    private ShortestQueueStrategy strategy2 = new ShortestQueueStrategy();

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for (int i = 0; i < maxNoServers; i++){
            Server thread = new Server();
            servers.add(thread);
        }
    }

    /*public void changeStrategy(SelectionPolicy policy){ //modifies the strategy
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new TimeStrategy();
        }
    }*/

    public boolean dispatchTask(Task t, SelectionPolicy selectionPolicy){ //call the strategy add task method
        if(selectionPolicy == SelectionPolicy.SHORTEST_TIME){
            this.strategy1.addTask(this.servers, t, maxTasksPerServer);
        }
        else{
            if(!this.strategy2.addTask(this.servers, t, maxTasksPerServer)){
                System.out.println("All servers are full at the moment");
                return false;
            }
        }
        return true;
    }

    public void printServers(){
        for (int i = 0; i < servers.size(); i++){
            System.out.println((i+1) + ". server, waiting time: " + servers.get(i).getWaitingPeriod().intValue());
            System.out.println("Tasks:");
            servers.get(i).printTasks();
        }
    }
    public ArrayList<Server> getServers(){
        return servers;
    }

}
