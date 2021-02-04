package task2;

public class Account {
    private String name;
    private double balance;
    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }
    public void income(int sum){
        balance +=sum;
    }

    public void outcome(int sum){
        balance -=sum;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
