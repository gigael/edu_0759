/*
ВНИМАНИЕ эта задача для ДЗ и не является обязательной.
Задание: 1. Создай массив чисел.
2. Добавь в массив 10 чисел с клавиатуры.
3. Вывести на экран длину самой длинной последовательности повторяющихся чисел в списке.

Пример для списка 2, 4, 4, 4, 8, 8, 4, 12, 12, 14:
3
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] myarray=new int[10];
        Scanner scan = new Scanner(System.in);
        int[] comparearray= new int [10];
        int maxvalue = 0;
        for (int i = 0; i < myarray.length; i++) {
            System.out.println("Введите "+(i+1)+"-ой/ий/ый элемент массива");
            myarray[i]=scan.nextInt();

        }
        for (int i = 1; i < myarray.length; i++) {
            if (myarray[i]==myarray[i-1]) {
                comparearray[i]=2;
                for (int j = i+1; j < (myarray.length-1) ; j++) {
                    if (myarray[j]==myarray[i]) comparearray[i]=comparearray[i]+1;
                    else break;

                }
            }
        }
        for (int i = 0; i < comparearray.length; i++) {
            if (comparearray[i]>maxvalue) maxvalue=comparearray[i];
        }
        if (maxvalue==0) System.out.println("повторяющихся чисел нет");
        else System.out.println("Самая длинная последовательность состоит из "+maxvalue+" повторяющихся чисел");

    }
}
