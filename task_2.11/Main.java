/*
Задание: Дан массив с числами. Узнайте сколько элементов с начала массива надо сложить, чтобы в сумме получилось больше 10-ти.
*/

public class Main {
    public static void main(String[] args) {
        int[] myarray={1,8,1,3};
        int sum=0, i;

        for (i = 0; i < myarray.length; i++) {
            sum=sum+myarray[i];
            if (sum>10) break;
        }
        if (sum<10) System.out.println("Сумма элементов менее 10");
        else System.out.println("Сумма превысила 10 после сложения "+(i+1)+" элементов");

    }
}
