public class SJFNP {

    //SJF s a scheduling policy that selects the waiting process with the smallest execution time to execute next.
    //non-preemptive version
    Process[] processes;//list of processes
    //i n k and j are variables for loops
    // btime - burst time
    //tt[] - turn-around time ( Time Difference between completion time and arrival time)
    //wt[]  - waiting time
    // wavg - average waiting time
    //tavg - average turn-around time
    //tsum - sum of turn-around times
    //wsum - sum of waiting times

    int i, n, j, min, k = 0, btime = 0;
    int[] wt, tt;
    int ta = 0, sum = 0;
    float wavg = 0, tavg = 0, tsum = 0, wsum = 0;
    int tmp;

    Process temp; //for swapping

    public SJFNP(Process[] p) {
        processes = p;
        wt = new int[p.length];
        tt = new int[p.length];
    }

    public void start() {
        for (j = 0; j < processes.length; j++) {
            btime = btime + processes[j].burstTime;
            min = processes[k].burstTime; //min is first element (k=0)
            for (i = k; i < processes.length; i++) { //0,1.2,...,proesses.length
                if (btime >= processes[i].arrivalTime && processes[i].burstTime < min) { //swapping according to burst time
                    temp = processes[k];
                    processes[k] = processes[i];
                    processes[i] = temp;
                    tmp = processes[k].arrivalTime;
                    processes[k].arrivalTime = processes[i].arrivalTime;
                    processes[i].arrivalTime = tmp;
                    tmp = processes[k].burstTime;
                    processes[k].burstTime = processes[i].burstTime;
                    processes[i].burstTime = tmp;
                }
            }
            k++; // to the next el
        }
        wt[0] = 0; // waiting time of first el is 0
        for (i = 1; i < processes.length; i++) {
            sum = sum + processes[i - 1].burstTime; //sum of burst times (needed to find avg)
            wt[i] = sum - processes[i].arrivalTime; //waiting time of element = sum of burst times minus arrival time
            wsum = wsum + wt[i]; // sum of waiting times (to find avg)
        }

        wavg = (wsum / processes.length);
        for (i = 0; i < processes.length; i++) {
            ta = ta + processes[i].burstTime;
            tt[i] = ta - processes[i].arrivalTime;
            tsum = tsum + tt[i];
        }

        wavg = (wsum / processes.length);
        for (i = 0; i < processes.length; i++) { //calculating sum of turn-around time
            ta = ta + processes[i].burstTime;
            tt[i] = ta - processes[i].arrivalTime;
            tsum = tsum + tt[i];
        }
        tavg = (tsum / processes.length); //calculating average turn-around
    }

    public void showResults() {
        System.out.println("---------SJF NON PRIMITIVE---------\nID\tBURST\tARRIVAL\tWAITING");
        for (int i = 0; i < processes.length; i++) {
           if(wt[i]<=0){
               System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime + "\t\t" +0);
           }
           else{
               System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime + "\t\t" +wt[i]);

           }

        }
        System.out.println("Average waiting time: "+wavg);
    }
}
