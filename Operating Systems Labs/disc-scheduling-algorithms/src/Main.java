
import java.util.*;

class DiscScheduler {


    class Task implements Comparable<Task> { //class of task

        int position; //position on disk
        int deadline;
        private final Random rand = new Random(); //to generate deadline

        Task(int position) {

            this.position = position;
            deadline = rand.nextInt(1000);

        }


        @Override
        public int compareTo(Task other) { //compare to sort by position
            return position - other.position;
        }


        @Override
        public boolean equals(Object other) {
            return other instanceof Task && position == ((Task) other).position;
        }


        @Override
        public String toString() {
            return "" + position;
        }

    }

    private int discSize = 200; //size of disk
    private Vector<Task> tasks = new Vector<Task>(); //vector list of tasks

    DiscScheduler(int discSize, int howManyTasks) {
        this.discSize = discSize; //set size of disc
        addRandom(howManyTasks); //sed count of tasks
    }


    @SuppressWarnings("unused")
    private void addRandom() {
        addRandom(100);
    }


    private void addRandom(int howMany) {
        Random rand = new Random();
        for (int x = 0; x < howMany; x++) //add tasks and generate random position in range from 0 to size of disc
            tasks.add(new Task(rand.nextInt(discSize)));

    }


    // Kind of like first in first out, position of the task doesn't matter
    //first come - first serve
    private int FCFS() {
        int time = 0; // how many time algorithm requires to be executed
        Vector<Task> tasks = new Vector<Task>(); //local list of tasks
        tasks.addAll(this.tasks); // adding tasks to the local list
        int pos = tasks.remove(0).position; // taking pos of 1st task and removing it from local list of tasks
        for (Task task : tasks) { //
            //for each task we calculate the time (task[n].pos - task[n-1].pos)
            //math.abs to take absolute value, because position of next task can be larger than current
            time += Math.abs(task.position - pos);
            pos = task.position;
        }
        return time; //return time taken to serve all tasks
    }


    // This algorithm takes "distance" between two tasks into account and moves
    // accordingly to closest task possible
    // visualisation https://media.geeksforgeeks.org/wp-content/uploads/3333-4.png
    private int SSTF() {

        int time = 0; // how many time algorithm requires to be executed
        Vector<Task> tasks = new Vector<Task>(); //local list of tasks
        tasks.addAll(this.tasks); //adding tasks to local list

        Task curr = tasks.remove(0); //current task (0 by default)

            //loop while we have tasks, position changes according to current task
            //simulation goes through the disc and looking for nearest task
            //if simulation found task than it summarizes time spent for finding
            //and goes to another task
        for (int x = 0, pos = curr.position; !tasks.isEmpty(); x++, pos = curr.position) {
            if (tasks.contains(new Task(pos + x))) { //if found task from the right (look for visualisation image)
                time += x;
                curr = new Task(pos + x);
                tasks.remove(new Task(pos + x));
                x = -1;
            } else if (tasks.contains(new Task(pos - x))) { //if found task from the left (look for visualisation image)
                time += x;
                curr = new Task(pos - x);
                tasks.remove(new Task(pos - x));
                x = -1;
            }
        }
        return time;


    }


    // This algorithm moves the head from the first task to the very left side of
    // the disc, then to the right
    //visualisation https://media.geeksforgeeks.org/wp-content/uploads/3333-4.png
    private int SCAN() {

        int time = 0; // how many time algorithm requires to be executed
        Vector<Task> tasks = new Vector<Task>(); //local list of task
        tasks.addAll(this.tasks); //copying tasks to the local list

        Task curr = tasks.remove(0); //curr task is first task by default


        for (int x = curr.position, pos = x; x >= 0; x--, pos = curr.position) { //moving to the left end from the left  side is start of the disc(0) (check visualisation)
            if (tasks.contains(new Task(x))) { //if we found task with position of x
                time += pos - x; //we take the distance from the current position to the position of the element found and summarize it as time
                curr = new Task(x); //found task is current task
                tasks.remove(curr); //remove found task from local list (as it was "done")
                x++; //move further on the disk
            }
        }

        curr = new Task(0);
        for (int x = 0, pos = 0; x <= discSize; x++, pos = curr.position) { //moving to the right (check visualisation) (end from the right side is size of disc)
            if (tasks.contains(new Task(x))) { //if we found task on current disc position
                time += x - pos; //calculating distance
                curr = new Task(x); //found task is current task
                tasks.remove(curr); //remove found task from local list (as it was "done")
                x--; //move further on the disc
            }
        }
        return time;

    }


    // This algorithm moves the head from the first task to the task that is closest
    // to the left side of the disc, and then to the most right task, and so on
    // visualisation https://media.geeksforgeeks.org/wp-content/uploads/20190820015715/fcfs2.png
    private int CSCAN() {

        int time = 0; //required time
        Vector<Task> tasks = new Vector<Task>(); //local list of tasks
        tasks.addAll(this.tasks);  //copying tasks to the local list

        Task curr = tasks.remove(0); //current task is the first task by default

        //moving from the first task to the end of the disc
        //and if found task - calculating time
        for (int x = curr.position; !tasks.isEmpty(); x++) {
            if (tasks.contains(new Task(x))) {
                time += x - curr.position;
                curr = new Task(x);
                tasks.remove(curr);
                x--;
            }
            if (x >= discSize) { //if end of the disc is reached  we move immediately to the beginning of disc
                x = -1;
                curr = new Task(0);
            }
        }
        return time;

    }


    // This algorithm moved head according to task's deadline, takes longer to
    // complete, but important tasks don't wait too long
    private int EDF() {

        Collections.sort(tasks, new Comparator<Task>() {

            @Override
            public int compare(Task task1, Task task2) {
                return task1.deadline - task2.deadline;
            }

        }); // just sorting by deadlines and tasks which have lowest deadline go first.
        return FCFS(); // and calling fcfs
    }


    @Override
    public String toString() {

        String string = "Disc has " + tasks.size() + " tasks.\n";
        for (Task task : tasks) string += task + ", ";
        return string;

    }


    public static class Main {
        public static void main(String[] args) {
            boolean is_end = false;
            int choose;
            int discSize = 500;
            int howManyTasks = 50;
            int howManySimulations = 50;

            String FCFS = "FSFC\t ";
            String SSTF = "SSTF\t ";
            String SCAN = "SCAN\t ";
            String CSCAN = "C-SCAN\t ";
            String EDF = "EDF\t ";

            Scanner sc = new Scanner(System.in);
            while (is_end != true) {
                System.out.println("\t\tMenu");
                System.out.println("1.Select disc size(" + discSize + " now)");
                System.out.println("2.Select count of tasks(" + howManyTasks + "now)");
                System.out.println("3.Select count of simulations(" + howManySimulations + "now)");
                System.out.println("4.Start simulation");
                System.out.println("5.Exit");
                System.out.print("\nChoose: ");

                try {
                    choose = Integer.parseInt(sc.nextLine());
                    switch (choose) {
                        case 1:
                            System.out.print("Choose disc size: ");
                            discSize = Integer.parseInt(sc.nextLine());
                            System.out.println("OK!");
                            break;
                        case 2:
                            System.out.print("Choose count of tasks: ");
                            howManyTasks = Integer.parseInt(sc.nextLine());
                            System.out.println("OK!");
                            break;
                        case 3:
                            System.out.print("Choose count of simulations: ");
                            howManySimulations = Integer.parseInt(sc.nextLine());
                            System.out.println("OK!");
                            break;
                        case 4:
                            System.out.println("Time required to process tasks (each time tasks are generated randomly (" + howManySimulations + " times)):");
                            for (int x = 0; x < howManySimulations; x++) {
                                DiscScheduler disc = new DiscScheduler(discSize, howManyTasks);
                                FCFS += disc.FCFS() + "\t";
                                SSTF += disc.SSTF() + "\t";
                                SCAN += disc.SCAN() + "\t";
                                CSCAN += disc.CSCAN() + "\t";
                                EDF += disc.EDF() + "\t";
                            }
                            System.out.println(FCFS);
                            System.out.println(EDF);
                            System.out.println(SSTF);
                            System.out.println(SCAN);
                            System.out.println(CSCAN);
                            FCFS = "FSFC\t ";
                            SSTF = "SSTF\t ";
                            SCAN = "SCAN\t ";
                            CSCAN = "C-SCAN\t ";
                            EDF = "EDF\t ";
                            break;
                        case 5:
                            is_end = true;
                    }
                } catch (Exception ex) {
                    System.out.println("Error!");
                }
            }
        }
    }
}
