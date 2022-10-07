package ro.tuc.pt.logic;

import ro.tuc.pt.model.Server;
import ro.tuc.pt.model.Task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeStrategy implements Strategy{
    public static float waitingTime = 0;
    public static float serviceTime = 0;

    @Override
    public boolean addTask(ArrayList<Server> servers, Task t, Integer tasksPerServer) {
        AtomicInteger waitingPeriod = new AtomicInteger(500);
        int ind = 0;
        for (int i = 0; i < servers.size(); i++){
            if (servers.get(i).getWaitingPeriod().intValue() < waitingPeriod.intValue()){
                waitingPeriod = servers.get(i).getWaitingPeriod();
                ind = i;
            }
        }
        waitingTime += waitingPeriod.intValue();
        System.out.println("Waiting: " + waitingTime);
        serviceTime += t.getServiceTime();
        servers.get(ind).addTask(t);
        return true;
    }
}
