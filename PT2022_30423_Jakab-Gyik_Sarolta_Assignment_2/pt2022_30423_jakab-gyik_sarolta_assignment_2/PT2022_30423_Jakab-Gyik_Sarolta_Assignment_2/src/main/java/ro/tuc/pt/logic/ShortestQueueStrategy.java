package ro.tuc.pt.logic;

import ro.tuc.pt.model.Server;
import ro.tuc.pt.model.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class ShortestQueueStrategy implements Strategy{
    public static float waitingTime = 0;
    public static float serviceTime = 0;

    @Override
    public boolean addTask(ArrayList<Server> servers, Task t, Integer tasksPerServer) {
        int minSize = tasksPerServer + 1, ind = -1;
        for (int i = 0; i < servers.size(); i++){
            if (servers.get(i).getTasks().size() < tasksPerServer && servers.get(i).getTasks().size() < minSize){
                minSize = servers.get(i).getTasks().size();
                ind = i;
            }
        }
        if (ind == -1){ //cannot add more tasks to servers - all full
            return false;
        }
        Iterator<Task> i = servers.get(ind).getTasks().iterator();
        float currWaitingTime = 0;
        while (i.hasNext()) {
            currWaitingTime += i.next().getServiceTime();
        }
        waitingTime += currWaitingTime;
        serviceTime += t.getServiceTime();
        servers.get(ind).addTask(t);
        return true;
    }
}
