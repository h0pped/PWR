public class Rotational {
    public Process[] processes; //list of processes
    int quantum_time; //quantum time
    int[]waitingTime; //waiting time for each process
    int[]turnaroundTime;//turn-around time for each process

    public Rotational(Process[] p,int quantum){
        processes = p;
        waitingTime = new int[p.length];
        turnaroundTime = new int[p.length];
        quantum_time=quantum;
    }

    public void FindWaiting(){ //find waiting time for processes
        int[]rem_burst = new int[processes.length]; //remaining burst for each proc
        int[]rem_arrival=new int[processes.length];//remaining arrival for each proc
        for(int i =0;i<rem_burst.length;i++){ //filling arrays by default values
            rem_burst[i] = processes[i].burstTime;
            rem_arrival[i] = processes[i].arrivalTime;
        }
        int curr_time =0;
        while(true){
            boolean done = true;
            for(int i = 0;i<processes.length;i++){
                if(rem_arrival[i]>0){ //to check has process already in queue, or it is not even arrived
                        if(rem_arrival[i]>quantum_time){   // if arrival for process > quantum of time then decreasing it's value
                            rem_arrival[i]-=quantum_time;
                        }
                        else{ //if arrival time < quantum then ve removing arrival time for process
                            curr_time-=(quantum_time-rem_arrival[i]);
                            rem_arrival[i]=0;
                        }
                        continue;
                }
                curr_time+=quantum_time; //increasing time by quantum
                if(rem_burst[i]>0){  //if process isn't done
                    done=false;
                    if(rem_burst[i]>quantum_time){
                        rem_burst[i]-=quantum_time;
                    }
                    else{
                        curr_time-=(quantum_time-rem_burst[i]);
                        waitingTime[i] = curr_time - processes[i].burstTime; //Waiting time is current time minus time used by this process
                        rem_burst[i]=0;
                    }
                }
            }
            if (done == true)
                break;
        }
    }
    public void findTurnAroundTime()
    {
        for (int i = 0; i<processes.length; i++)
            turnaroundTime[i] = processes[i].burstTime + waitingTime[i];
    }
    public void showAVG(){
        int total_wt=0,total_at=0;
        for(int i =0;i<processes.length;i++){
            total_at+=turnaroundTime[i];
            total_wt+=waitingTime[i];
        }
        System.out.println("Average wait time: "+(total_wt/processes.length));
        System.out.println("Average turn-around time: "+(total_at/processes.length));
    }
    public void showResults(){
        System.out.println("---------ROTATIONAL---------\nID\tBURST\tARRIVAL\tWAITING\tTURN-AROUND");
        for (int i = 0; i < processes.length; i++) {
                System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime + "\t\t" + waitingTime[i]+"\t\t"+turnaroundTime[i]);

            }
        showAVG();
    }
}
