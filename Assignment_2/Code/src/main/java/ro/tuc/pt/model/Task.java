package ro.tuc.pt.model;

public class Task {
    private int id;
    private Integer arrivalTime;
    private Integer serviceTime;

    //to generate the unique IDs
    private static long idCounter = 0;

    public static synchronized Integer createID()
    {
        return (int)idCounter++;
    }

    public Task(Integer arrivalTime, Integer serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.id = createID();
    }

    public void printTask(){
        System.out.println("(" + this.id + ", " + this.arrivalTime + ", " + this.serviceTime + ")");
    }

    public int getId() {
        return id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }
}
