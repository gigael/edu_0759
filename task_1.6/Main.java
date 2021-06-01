/*
Пользователь вводит число, необходимо увеличить это число на 15% и вывести результат на экран
*/

import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        double b = a + (Double)Math.floor(a*15)/100;
        System.out.println(b);
    }
}
