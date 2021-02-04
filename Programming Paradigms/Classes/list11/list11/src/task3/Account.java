package task3;

public class Account {
    private String name;
    private double balance;
    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }
    public synchronized void income(int sum){  //synchronized so two threads cannot do the same method at the same time
        balance +=sum;
    }

    public synchronized void outcome(int sum){
        balance -=sum;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
