import java.util.Scanner;

public class Main {
    public static void SortProcessesByArrival(Process[] p) {
        p[0].arrivalTime = 0; //first process always should have arrival time 0

        for (int i = 0; i < p.length; i++) { //bubble sort by arrival time
            for (int j = 0; j < p.length - 1; j++) {
                if (p[j].arrivalTime > p[j + 1].arrivalTime) {
                    //swapping
                    Process pk = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = pk;
                }
            }
        }
        for (int i = 0; i < p.length; i++) {
            p[i].id = i; //reset id's according to arrival time
        }
    }

    public static void LoadProcesses(Process[] p) { //generate processes
        for (int i = 0; i < p.length; i++) {
            p[i] = new Process();
            p[i].id = i;
        }
    }

    public static void main(String[] args) {
        boolean end = false;
        int processes_num = 0;
        Process[] processes = null;
        boolean is_generated = false;
        Scanner sc = new Scanner(System.in);
        int menu_num;
        while (!end) {
            System.out.println("\t\tMenu");
            System.out.println("1.Choose number of processes(now: " + processes_num + ")");
            System.out.println("2.Generate Processes");
            System.out.println("3.Show Processes");
            System.out.println("4.Show Results of scheduling");
            System.out.print("Choose: ");
            try {
                menu_num = Integer.parseInt(sc.nextLine());
                switch (menu_num) {
                    case 1:
                        int number;
                        System.out.print("Number of processes: ");
                        try {
                            number = Integer.parseInt(sc.nextLine());
                            if (number > 0) {

                                processes_num = number;
                                System.out.println("Success!\n");
                            } else {
                                System.out.println("Error!");
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                        break;
                    case 2:
                        try {
                            if (processes_num > 0) {
                                processes = new Process[processes_num];
                                LoadProcesses(processes);
                                SortProcessesByArrival(processes);
                                System.out.println("Success!\n");
                            } else {
                                System.out.println("Firstly you should choose number of processes!\n");
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                        break;
                    case 3:
                        if (processes != null) {
                            System.out.println("\nID\tBURST\tARRIVAL");

                            for (int i = 0; i < processes.length; i++) {
                                System.out.println(processes[i].id + "\t" + processes[i].burstTime + "\t\t" + processes[i].arrivalTime);

                            }
                        } else {
                            System.out.println("Firstly you need to generate processes!\n\n");
                        }
                        break;
                    case 4:
                        boolean quantum = false;
                        int quantum_num = 1;
                        while (!quantum) {

                            try {
                                System.out.print("Choose quantum time value: ");

                                quantum_num = Integer.parseInt(sc.nextLine());
                                quantum = true;
                            } catch (Exception ex) {
                                System.out.println("error!");
                            }
                        }


                        FCFS f = new FCFS(processes);
                        f.FindWaitingTime();
                        f.drawResults();

                        System.out.println("\n\n");

                        SJFNP sjfnp = new SJFNP(processes);
                        sjfnp.start();
                        sjfnp.showResults();

                        Rotational rot = new Rotational(processes, quantum_num);
                        rot.FindWaiting();
                        rot.findTurnAroundTime();
                        rot.showResults();
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }

        }
        System.out.println("Bye!");
    }
}
