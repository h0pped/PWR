package task1;

public class Main {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        task.run(); // runs first by main
        Thread thread = new Thread(task);
        thread.start(); //runnable state, but not run yet
        System.out.println("Done!"); // by main

        //hello main
        //done
        //hello thread-0
    }
}