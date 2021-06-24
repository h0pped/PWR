package class1;
import java.util.Scanner;
public class DecimalToP {

    public static void decimalToP(){
        int dec = 0;
        int p = 0;
        int i = 1;
        int j;
        int newNum = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Input Decimal Number: ");
        dec = sc.nextInt();
        System.out.println("Input 1<p<10: ");
        p = sc.nextInt();

        j = dec; //temp var
        do{
            newNum = newNum+(j%p)*i;
            i *=10;
            j/=p;
        }
        while(j!=0);

        System.out.println("Decimal "+dec + " = "+ p +"'th "+ newNum);
    }

    public static void main(String[]args){
        decimalToP();
    }
}
