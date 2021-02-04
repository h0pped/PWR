package task4;

public class Philosopher implements Runnable{

    private final Object leftFork;
    private final Object rightFork;

    Philosopher(Object left, Object right){
        this.leftFork = left;
        this.rightFork = right;
    }

    private void doAction(String action) throws  InterruptedException{
        System.out.println(Thread.currentThread().getName()+ " " + action);
        Thread.sleep((int)(Math.random()*100));
    }

    public void run(){
        try{
            while(true){
                doAction(System.nanoTime()+": Thinking");
                synchronized (leftFork){
                    doAction(System.nanoTime()+": Picked up leftFork");
                    synchronized (rightFork){
                        doAction(System.nanoTime()+": Picked up rightFork and eating");
                        doAction(System.nanoTime()+": Put down rightFork");
                    }
                    doAction(System.nanoTime()+": Put down leftFork and thinking");
                }
            }
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
