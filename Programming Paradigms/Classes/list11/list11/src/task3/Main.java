package task3;

//now there will be no such problem because of "synchronized" tag

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
