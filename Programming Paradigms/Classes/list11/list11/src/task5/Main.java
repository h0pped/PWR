package task5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
public static void main(String[]args){

    //runs without delay
    ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = ()->{
            for(int i = 0;i<30;i++){
                System.out.println(i);
                try{
                    TimeUnit.MILLISECONDS.sleep(1000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
    };
    Future<?> f1 = executor.submit(task);
    executor.shutdown();
}
}
