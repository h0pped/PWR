public class Process {

    int id,burstTime, arrivalTime; //each process has own id, burst- and arrival- time which are randomly generated in constructor

    public Process() {
        burstTime = (int)(Math.random() * 10+1);
        arrivalTime = (int)(Math.random() * 200);
    }


}
