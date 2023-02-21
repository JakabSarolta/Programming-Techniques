package ro.tuc.pt.logic;

import ro.tuc.pt.model.Server;
import ro.tuc.pt.model.Task;

import java.util.ArrayList;

public interface Strategy {
    public boolean addTask(ArrayList<Server> servers, Task t, Integer tasksPerServer);
}
