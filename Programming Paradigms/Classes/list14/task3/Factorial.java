package ex14.task3;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Factorial {
    public static void ReactiveFactorial() throws OnErrorNotImplementedException, InterruptedException {
        //from 1 to ....
        //skip first element to skip '0'
        //subscribe to print result after each call
        //onError end
            Observable.interval(1, TimeUnit.SECONDS).skip(1).scan(1, (sum, nextEl) -> Math.toIntExact(sum * nextEl)).onErrorComplete(error-> error instanceof IOException).subscribe(x -> System.out.print(x+" "),error->System.out.println("End"));
        Thread.sleep(20000);
    }
    public static void main(String[]args) throws InterruptedException {
            ReactiveFactorial();
    }
}
