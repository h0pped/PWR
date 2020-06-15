public class FCFS {

    //FCFI is  simply queues processes in the order that they arrive in the ready queue.
    //The process that comes first will be executed first and next process starts only after the previous gets fully executed.

    public Process[] processes; //list of processes
    public double[] waitingTime; // how much time process should wait to be executed
    public double[] serviceTime; // service time for each process

    public FCFS(Process[] p) {
        processes = p;
        waitingTime = new double[p.length];
        serviceTime = new double[p.length];
    }


    public void FindWaitingTime() {
        waitingTime[0] = 0; //waiting time for 1st element is 0;
        serviceTime[0] = 0; // same for service time

        for (int i = 1; i < processes.length; i++) {
            serviceTime[i] = processes[i - 1].burstTime + serviceTime[i - 1]; //service time is the sum of burst time and service time of previous element
            waitingTime[i] = serviceTime[i] - processes[i].arrivalTime;
        }
    }
    public Process max_burst(){ //to find the process with max_burst time
        Process p = new Process();
        p.burstTime = Integer.MIN_VALUE;
        for(int i =0;i<processes.length;i++){
            if(processes[i].burstTime>p.burstTime){
                p = processes[i];
            }
        }
        return p;
    }
    public double avgWait(){ //average waiting value for each process
        int sum=0;
        for(int i =0;i<processes.length;i++){
            sum+=waitingTime[i];
        }
        return sum/waitingTime.length;
    }

    public void drawResults() {
        System.out.println("---------FIRST CAME - FIRST SERVE---------\nID\tBURST\tARRIVAL\tWAITING");
        for (int i = 0; i < processes.length; i++) {
            if(waitingTime[i]<0){
                System.out.println("PROCESSOR REST PHASE FOR: "+Math.abs(waitingTime[i]-waitingTime[i-1]));
                System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime + "\t\t" + "0.0");

            }
            else{

            System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime + "\t\t" + waitingTime[i]);
            }
        }
        System.out.println("\nMAX BURST TIME: ");
        System.out.println("ID\tBURST\tARRIVAL\tWAITING");
        Process p = max_burst();
        System.out.print(p.id + "\t" + p.burstTime + "\t\t" + p.arrivalTime + "\t\t");
        if( waitingTime[p.id]<0){
            System.out.print("0\n");
        }
        else
        {
            System.out.print( waitingTime[p.id]+"\n");
        }
        System.out.println("\nAVERAGE WAITING TIME: "+avgWait());
        System.out.println("\n--------------------------------\n");
    }
}