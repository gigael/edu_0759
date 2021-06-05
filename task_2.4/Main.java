/*
Задание: Пользователь вводит сумму вклада и процент, который будет начисляться ежегодно. Отобразить размер вклада поочередно на ближайшие 5 лет.
*/

import java.text.DecimalFormat;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите сумму вклада");
        Scanner scan = new Scanner(System.in);
        Double deposit = scan.nextDouble();
        System.out.println("Введите процентную ставку (%)");
        Double rate = scan.nextDouble();
        for (int i = 1; i < 6 ; i++) {
            deposit = deposit*(1+rate/100);
            String depositr="";
            //обычно денежные суммы отображают с 2-мя знаками после запятой
            depositr= new DecimalFormat( ".##").format(deposit);
            System.out.println("Через "+i+" год(а)/лет сумма вклада " +
                    "составит "+depositr);
        }

    }
}
