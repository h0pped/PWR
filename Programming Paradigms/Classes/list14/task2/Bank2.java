package ex14.task2;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank2 {
    public static void main(String[]args){
        AtomicInteger currentFirst = new AtomicInteger();
        AtomicInteger currentSecond = new AtomicInteger();
        Observable.just(100,15,47,1500).subscribe(x -> {
            currentFirst.set(x);
            System.out.println("New #1: "+currentFirst.get()+". Bank: "+ (currentFirst.get()+currentSecond.get()));
        });
        Observable.just(27,23,1,5,7,25,70).subscribe(x -> {
            currentSecond.set(x);
            System.out.println("New #2: "+currentSecond.get()+". Bank: "+ (currentFirst.get()+currentSecond.get()));
        });
    }
}
