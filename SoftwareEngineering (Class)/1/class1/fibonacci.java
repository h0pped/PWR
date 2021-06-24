package class1;
import java.util.Scanner;
public class fibonacci {

    public static void fibonacci(){
        Scanner sc = new Scanner(System.in);
        int fib=0;
        int a = 0;
        int b =1;
        int N = 0;
        int i = 0;


        System.out.print("Enter N: ");
        N = sc.nextInt();

        while(N<0){
            System.out.print("N Should be 0 or bigger");
            System.out.print("\nEnter N: ");
            N = sc.nextInt();
        }
        while(N>i){
            fib = a+b;
            a = b;
            b = fib;
            i = i+1;
        }
        System.out.println("Fibonacci("+N+") = "+ fib);
    }

    public static void main(String[]args){
        fibonacci();

    }
}
