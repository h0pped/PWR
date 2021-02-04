package ex14.task1;



import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    public static void main(String[]args){
        AtomicInteger totalFirst = new AtomicInteger();
        AtomicInteger totalSecond = new AtomicInteger();
        Observable.just(10,20,7,-5,3,3,2,18).subscribe(x -> {
            totalFirst.addAndGet(x);
            System.out.println("Account #1: $"+ x+". Bank: $"+(totalFirst.get() + totalSecond.get()));
        });
        Observable.just(5,5,5,5,5-20,2).subscribe(x -> {
            totalSecond.addAndGet(x);
            System.out.println("Account #2: $"+ x+". Bank: $"+(totalFirst.get() + totalSecond.get()));
        });
    }
}
