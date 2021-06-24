package class1;

import java.util.Scanner;
public class isPrime {

    public static void isPrimeNumber(){
        int num = 0;
        int i = 2;
        Scanner sc = new Scanner(System.in);

        System.out.print("Input your number: ");
        num = sc.nextInt();

        while(num<=1){
            System.out.println("Prime numbers are > 1");
            System.out.print("Input your number: ");
            num = sc.nextInt();
        }
        while(num>i){
            if(num%i>0){
                i = i+1;
            }
            else{
                System.out.println(num +" is divisible by "+i+", not a prime number");
                return;
            }
        }
        System.out.println(num + " is a prime number");
    }

    public static void main(String[]args){
    isPrimeNumber();
    }
}
