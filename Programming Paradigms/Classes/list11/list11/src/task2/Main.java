package task2;

//final balance will not always be 100, and because of lack of synchronization
// income and outcome can try to make operation at the same time which is not good and real balance can be lost
//we should use synchronization

public class Main {
    public static void main(String[]args){
        Account acc1 = new Account("first",100);

        Thread outcomeThread = new Thread(()->{
           for(int i =0;i<100;i++){
               acc1.outcome(1);
               System.out.println("Outcome balance: "+acc1.getBalance());
           }
        });
        Thread incomeThread = new Thread(()->{
            for(int i =0;i<100;i++){
                acc1.income(1);
                System.out.println("Income balance: "+acc1.getBalance());
            }
        });

        outcomeThread.start();
        incomeThread.start();

        try {
            incomeThread.join();
            outcomeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //waiting when threads will stop their work to print final balance

        System.out.println("Balance: " + acc1.getBalance());
    }
}
