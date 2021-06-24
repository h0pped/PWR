package class1;
import java.util.Scanner;
public class primeFactors {

    public static void primeFactors(){
        int N = 0;
        int f = 2;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N >1: ");
        N = sc.nextInt();

        while(N<=1){
            System.out.println("N should be >1! ");
            System.out.print("Enter N >1: ");
            N = sc.nextInt();
        }
        System.out.print(N + "=");

        do{
            if(N%f ==0){
                System.out.print(f+"*");
                N = N/f; //divide by prime;
            }
            else{
                f = f+1;
            }
        }while(N>=f*f);

        System.out.print(N);
    }


    public static void main(String[]args){
    primeFactors();
    }
}
