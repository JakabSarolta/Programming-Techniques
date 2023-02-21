package ro.tuc.pt.model;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread{ //better than implements Runnable
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod = new AtomicInteger();

    public Server() {
        this.tasks = new LinkedBlockingDeque<>();
        this.waitingPeriod.set(0); //with 0
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime()); //service time corresponding to task
    }

    @Override
    public void run() {
        while (true){
            Task currentTask = tasks.poll(); //retrieves and removes head of the queue - take method
            if (currentTask == null){ //empty queue
                break;
            }
            try{
                while (currentTask.getServiceTime() > 0){
                    Thread.sleep(1000); //stop the thread for the time of the execution
                    waitingPeriod.decrementAndGet();
                    currentTask.setServiceTime(currentTask.getServiceTime() - 1);
                }
            } catch(Exception e){
                System.out.println("No service time specified for the task");
            }

        }
    }

    public BlockingQueue<Task> getTasks() { //change the type to list if u want to
        return tasks;
    } //change the type to list if u want to

    public void printTasks() {
        Iterator<Task> i = tasks.iterator();
        i.next().printTask();
        while (i.hasNext()) {
            i.next().printTask();
        }
    }

    public String getStringTasks(){
        String taskString = "";
        Iterator<Task> i = tasks.iterator();
        Task currTask = i.next();
        taskString += "(" + currTask.getId() + ", " + currTask.getArrivalTime() + ", " + currTask.getServiceTime() + ") ";
        while (i.hasNext()) {
            currTask = i.next();
            taskString += "(" + currTask.getId() + ", " + currTask.getArrivalTime() + ", " + currTask.getServiceTime() + ") ";
        }
        return taskString;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
}
