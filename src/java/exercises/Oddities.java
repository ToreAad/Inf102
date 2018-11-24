package torea;

import java.util.Scanner;

public class Oddities{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        sc.next();
        while(sc.hasNextInt())
        {
            int n = sc.nextInt();
            System.out.println(oddOrEven(n));
        }
        sc.close();
    }

    public static String oddOrEven(int n){
        if (n%2 == 0){
            return String.format("%d is even", n);
        } else {
            return String.format("%d is odd", n);
        }
    }
}